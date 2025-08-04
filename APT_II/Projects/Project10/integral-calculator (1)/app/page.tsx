"use client"

import { useState, useEffect, useRef } from "react"
import { Button } from "@/components/ui/button"
import { Input } from "@/components/ui/input"
import { Label } from "@/components/ui/label"
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select"
import { Slider } from "@/components/ui/slider"
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card"
import { Calculator, Play } from "lucide-react"

interface CalculationResult {
  area: number
  points: Array<{ x: number; y: number }>
  partialAreas: number[]
  latexFormula: string
  method: string
}

export default function IntegralCalculator() {
  const [functionInput, setFunctionInput] = useState("x^2 + 3*x")
  const [lowerLimit, setLowerLimit] = useState("0")
  const [upperLimit, setUpperLimit] = useState("2")
  const [partitions, setPartitions] = useState([10])
  const [method, setMethod] = useState("trapecio")
  const [result, setResult] = useState<CalculationResult | null>(null)
  const [loading, setLoading] = useState(false)
  const [error, setError] = useState("")

  const mathFieldRef = useRef<any>(null)
  const chartRef = useRef<any>(null)
  const canvasRef = useRef<HTMLCanvasElement>(null)

  // Initialize MathLive
  useEffect(() => {
    const loadMathLive = async () => {
      if (typeof window !== "undefined") {
        try {
          const { MathfieldElement } = await import("mathlive")

          if (mathFieldRef.current && !mathFieldRef.current.hasAttribute("data-initialized")) {
            mathFieldRef.current.setAttribute("data-initialized", "true")
            mathFieldRef.current.value = functionInput

            // Configure MathLive options
            mathFieldRef.current.setOptions({
              virtualKeyboardMode: "manual",
              virtualKeyboards: "numeric functions symbols",
              smartFence: true,
              smartSuperscript: true,
            })

            mathFieldRef.current.addEventListener("input", (evt: any) => {
              const value = evt.target.value || evt.target.getValue?.() || functionInput
              setFunctionInput(value)
            })

            mathFieldRef.current.addEventListener("focus", () => {
              if (window.mathVirtualKeyboard) {
                window.mathVirtualKeyboard.show()
              }
            })
          }
        } catch (error) {
          console.error("MathLive loading error:", error)
        }
      }
    }

    const timer = setTimeout(loadMathLive, 1000)
    return () => clearTimeout(timer)
  }, [])

  // Initialize KaTeX for LaTeX rendering
  useEffect(() => {
    const script = document.createElement("script")
    script.src = "https://cdn.jsdelivr.net/npm/katex@0.16.8/dist/katex.min.js"
    script.onload = () => {
      const link = document.createElement("link")
      link.rel = "stylesheet"
      link.href = "https://cdn.jsdelivr.net/npm/katex@0.16.8/dist/katex.min.css"
      document.head.appendChild(link)
    }
    document.head.appendChild(script)

    // Load MathLive
    const mathLiveScript = document.createElement("script")
    mathLiveScript.src = "https://unpkg.com/mathlive@0.95.0/dist/mathlive.min.js"
    document.head.appendChild(mathLiveScript)

    const mathLiveCSS = document.createElement("link")
    mathLiveCSS.rel = "stylesheet"
    mathLiveCSS.href = "https://unpkg.com/mathlive@0.95.0/dist/mathlive-static.css"
    document.head.appendChild(mathLiveCSS)
  }, [])

  const calculateIntegral = async () => {
    setLoading(true)
    setError("")

    // Validate inputs before sending
    if (!functionInput.trim()) {
      setError("Por favor ingresa una función")
      setLoading(false)
      return
    }

    if (isNaN(Number(lowerLimit)) || isNaN(Number(upperLimit))) {
      setError("Los límites deben ser números válidos")
      setLoading(false)
      return
    }

    if (Number(lowerLimit) >= Number(upperLimit)) {
      setError("El límite inferior debe ser menor que el superior")
      setLoading(false)
      return
    }

    try {
      const response = await fetch("/api/calculate", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          function: functionInput,
          lowerLimit: Number.parseFloat(lowerLimit),
          upperLimit: Number.parseFloat(upperLimit),
          partitions: partitions[0],
          method: method,
        }),
      })

      const data = await response.json()

      if (!response.ok) {
        throw new Error(data.error || "Error en el cálculo")
      }

      setResult(data)

      // Draw chart after getting results
      setTimeout(() => drawChart(data), 100)
    } catch (err) {
      const errorMessage = err instanceof Error ? err.message : "Error desconocido al calcular la integral"
      setError(errorMessage)
      console.error("Calculation error:", err)
    } finally {
      setLoading(false)
    }
  }

  const drawChart = (data: CalculationResult) => {
    const canvas = canvasRef.current
    if (!canvas) return

    const ctx = canvas.getContext("2d")
    if (!ctx) return

    // Set high DPI for crisp rendering
    const dpr = window.devicePixelRatio || 1
    const rect = canvas.getBoundingClientRect()
    canvas.width = rect.width * dpr
    canvas.height = rect.height * dpr
    ctx.scale(dpr, dpr)

    const width = rect.width
    const height = rect.height
    const padding = 80

    // Clear canvas with white background
    ctx.fillStyle = "#ffffff"
    ctx.fillRect(0, 0, width, height)

    // Find bounds with some padding
    const xMin = Math.min(...data.points.map((p) => p.x))
    const xMax = Math.max(...data.points.map((p) => p.x))
    const yMin = Math.min(0, ...data.points.map((p) => p.y)) * 1.1
    const yMax = Math.max(...data.points.map((p) => p.y)) * 1.1

    // Scale functions
    const scaleX = (x: number) => padding + ((x - xMin) / (xMax - xMin)) * (width - 2 * padding)
    const scaleY = (y: number) => height - padding - ((y - yMin) / (yMax - yMin)) * (height - 2 * padding)

    // Draw grid
    ctx.strokeStyle = "#f0f0f0"
    ctx.lineWidth = 1
    const gridSteps = 10
    for (let i = 0; i <= gridSteps; i++) {
      const x = padding + (i / gridSteps) * (width - 2 * padding)
      const y = padding + (i / gridSteps) * (height - 2 * padding)

      // Vertical grid lines
      ctx.beginPath()
      ctx.moveTo(x, padding)
      ctx.lineTo(x, height - padding)
      ctx.stroke()

      // Horizontal grid lines
      ctx.beginPath()
      ctx.moveTo(padding, y)
      ctx.lineTo(width - padding, y)
      ctx.stroke()
    }

    // Draw axes
    ctx.strokeStyle = "#333"
    ctx.lineWidth = 3
    ctx.beginPath()
    // X-axis
    ctx.moveTo(padding, scaleY(0))
    ctx.lineTo(width - padding, scaleY(0))
    // Y-axis
    ctx.moveTo(scaleX(0), padding)
    ctx.lineTo(scaleX(0), height - padding)
    ctx.stroke()

    // Draw axis labels with values
    ctx.fillStyle = "#333"
    ctx.font = "14px Arial"
    ctx.textAlign = "center"

    // X-axis labels
    for (let i = 0; i <= 5; i++) {
      const x = xMin + (i / 5) * (xMax - xMin)
      const screenX = scaleX(x)
      ctx.fillText(x.toFixed(1), screenX, scaleY(0) + 25)
    }

    // Y-axis labels
    ctx.textAlign = "right"
    for (let i = 0; i <= 5; i++) {
      const y = yMin + (i / 5) * (yMax - yMin)
      const screenY = scaleY(y)
      if (Math.abs(y) > 0.01) {
        ctx.fillText(y.toFixed(1), scaleX(0) - 10, screenY + 5)
      }
    }

    // Draw trapezoids/rectangles first (behind the curve)
    if (method === "trapecio") {
      const step = (Number.parseFloat(upperLimit) - Number.parseFloat(lowerLimit)) / partitions[0]

      for (let i = 0; i < partitions[0]; i++) {
        const x1 = Number.parseFloat(lowerLimit) + i * step
        const x2 = Number.parseFloat(lowerLimit) + (i + 1) * step

        const y1 = evaluateFunction(functionInput, x1)
        const y2 = evaluateFunction(functionInput, x2)

        // Alternate colors for better visualization
        const hue = (i * 360) / partitions[0]
        ctx.fillStyle = `hsla(${hue}, 60%, 70%, 0.4)`
        ctx.strokeStyle = `hsla(${hue}, 60%, 50%, 0.8)`
        ctx.lineWidth = 2

        ctx.beginPath()
        ctx.moveTo(scaleX(x1), scaleY(0))
        ctx.lineTo(scaleX(x1), scaleY(y1))
        ctx.lineTo(scaleX(x2), scaleY(y2))
        ctx.lineTo(scaleX(x2), scaleY(0))
        ctx.closePath()
        ctx.fill()
        ctx.stroke()

        // Add area labels on trapezoids
        if (partitions[0] <= 20) {
          ctx.fillStyle = "#333"
          ctx.font = "12px Arial"
          ctx.textAlign = "center"
          const midX = (x1 + x2) / 2
          const midY = Math.max(y1, y2) / 2
          ctx.fillText(`A${i + 1}`, scaleX(midX), scaleY(midY))
        }
      }
    }

    // Draw function curve
    ctx.strokeStyle = "#1e40af"
    ctx.lineWidth = 4
    ctx.beginPath()
    data.points.forEach((point, index) => {
      const x = scaleX(point.x)
      const y = scaleY(point.y)
      if (index === 0) {
        ctx.moveTo(x, y)
      } else {
        ctx.lineTo(x, y)
      }
    })
    ctx.stroke()

    // Add function points
    ctx.fillStyle = "#1e40af"
    data.points.forEach((point, index) => {
      if (index % 5 === 0) {
        // Show every 5th point
        const x = scaleX(point.x)
        const y = scaleY(point.y)
        ctx.beginPath()
        ctx.arc(x, y, 3, 0, 2 * Math.PI)
        ctx.fill()
      }
    })

    // Add axis titles
    ctx.fillStyle = "#333"
    ctx.font = "16px Arial"
    ctx.textAlign = "center"
    ctx.fillText("x", width - 30, scaleY(0) + 50)

    ctx.save()
    ctx.translate(25, height / 2)
    ctx.rotate(-Math.PI / 2)
    ctx.fillText("f(x)", 0, 0)
    ctx.restore()

    // Add title
    ctx.font = "18px Arial"
    ctx.fillStyle = "#1e40af"
    ctx.textAlign = "center"
    ctx.fillText(`f(x) = ${functionInput}`, width / 2, 30)
  }

  const evaluateFunction = (func: string, x: number): number => {
    try {
      // Clean and prepare the function string
      let expression = func
        .toLowerCase()
        .replace(/\s+/g, "") // Remove spaces
        .replace(/\^/g, "**") // Replace ^ with **
        .replace(/sin/g, "Math.sin")
        .replace(/cos/g, "Math.cos")
        .replace(/tan/g, "Math.tan")
        .replace(/log/g, "Math.log10")
        .replace(/ln/g, "Math.log")
        .replace(/sqrt/g, "Math.sqrt")
        .replace(/abs/g, "Math.abs")
        .replace(/pi/g, "Math.PI")
        .replace(/e(?!\d)/g, "Math.E") // Replace e but not in numbers like e10
        .replace(/(\d)([a-z])/g, "$1*$2") // Add multiplication: 2x -> 2*x
        .replace(/([a-z])(\d)/g, "$1*$2") // Add multiplication: x2 -> x*2
        .replace(/\)(\d)/g, ")*$1") // Add multiplication: )2 -> )*2
        .replace(/(\d)\(/g, "$1*(") // Add multiplication: 2( -> 2*(

      // Replace x with the actual value
      expression = expression.replace(/x/g, `(${x})`)

      // Evaluate the expression
      const result = Function(`"use strict"; return (${expression})`)()

      if (isNaN(result) || !isFinite(result)) {
        throw new Error("Invalid result")
      }

      return result
    } catch (error) {
      console.error("Function evaluation error:", error)
      return 0
    }
  }

  const renderLatex = (latex: string) => {
    if (typeof window !== "undefined" && (window as any).katex) {
      try {
        return {
          __html: (window as any).katex.renderToString(latex, {
            throwOnError: false,
            displayMode: true,
          }),
        }
      } catch {
        return { __html: latex }
      }
    }
    return { __html: latex }
  }

  return (
    <div className="min-h-screen bg-gradient-to-br from-blue-50 to-indigo-100 p-4">
      <div className="max-w-7xl mx-auto">
        {/* Bloque institucional */}
        <section className="bg-white rounded-lg shadow-md p-6 mb-8 border border-blue-200">
          <div className="text-center text-gray-800">
            <h2 className="text-xl font-bold mb-1">UNIVERSIDAD TECNOLÓGICA DE PANAMÁ</h2>
            <div className="font-semibold">FACULTAD DE INGENIERÍA DE SISTEMAS COMPUTACIONALES</div>
            <div className="mb-2">DEPARTAMENTO DE PROGRAMACIÓN DE COMPUTADORAS</div>
            <div className="mb-1">Facilitador(a): Emilio Batista</div>
            <div className="mb-1">Asignatura: Herramientas de Programación 2</div>
            <div className="mb-1">Estudiante:</div>
            <ul className="mb-1">
              <li>• Jhuomar Barría | 9-766-196</li>
              <li>• Terry he | 8-1021-2180</li>
              <li>• Geremi Tejeira | 9-768-42</li>
            </ul>
            <div className="mb-1">Fecha: 18/06/25</div>
            <div>Grupo: 1IL-128</div>
          </div>
        </section>
        {/* Fin bloque institucional */}
        <header className="text-center mb-8">
          <div className="flex items-center justify-center gap-3 mb-4">
            <Calculator className="h-8 w-8 text-blue-600" />
            <h1 className="text-4xl font-bold text-gray-800">Calculadora de Integrales</h1>
          </div>
          <p className="text-gray-600 text-lg">
            Calcula integrales definidas usando métodos numéricos con visualización gráfica
          </p>
        </header>

        <div className="grid grid-cols-1 xl:grid-cols-3 gap-8">
          {/* Input Panel - takes 1 column */}
          <Card className="shadow-lg xl:col-span-1">
            <CardHeader>
              <CardTitle className="flex items-center gap-2">
                <Calculator className="h-5 w-5" />
                Parámetros de Entrada
              </CardTitle>
            </CardHeader>
            <CardContent className="space-y-6">
              {/* Math Input with fallback */}
              <div className="space-y-2">
                <Label htmlFor="function">Función f(x)</Label>
                <div className="border rounded-lg p-2 bg-white">
                  <math-field
                    ref={mathFieldRef}
                    id="function"
                    className="w-full min-h-[60px] text-lg"
                    style={{ border: "none", outline: "none" }}
                  >
                    {functionInput}
                  </math-field>
                  <div className="flex gap-2 mt-2">
                    <Button
                      type="button"
                      variant="outline"
                      size="sm"
                      onClick={() => {
                        if (mathFieldRef.current) {
                          mathFieldRef.current.focus()
                          if (window.mathVirtualKeyboard) {
                            window.mathVirtualKeyboard.show()
                          }
                        }
                      }}
                    >
                      Mostrar Teclado
                    </Button>
                    <Button
                      type="button"
                      variant="outline"
                      size="sm"
                      onClick={() => {
                        if (window.mathVirtualKeyboard) {
                          window.mathVirtualKeyboard.hide()
                        }
                      }}
                    >
                      Ocultar Teclado
                    </Button>
                  </div>
                  {/* Fallback input */}
                  <Input
                    type="text"
                    value={functionInput}
                    onChange={(e) => setFunctionInput(e.target.value)}
                    placeholder="Ejemplo: x^2 + 3*x"
                    className="mt-2"
                  />
                </div>
                <p className="text-sm text-gray-500">Ejemplo: x^2 + 3*x, sin(x), e^x, etc.</p>
              </div>

              {/* Limits */}
              <div className="grid grid-cols-2 gap-4">
                <div className="space-y-2">
                  <Label htmlFor="lower">Límite inferior (a)</Label>
                  <Input
                    id="lower"
                    type="number"
                    step="0.1"
                    value={lowerLimit}
                    onChange={(e) => setLowerLimit(e.target.value)}
                    className="text-center"
                  />
                </div>
                <div className="space-y-2">
                  <Label htmlFor="upper">Límite superior (b)</Label>
                  <Input
                    id="upper"
                    type="number"
                    step="0.1"
                    value={upperLimit}
                    onChange={(e) => setUpperLimit(e.target.value)}
                    className="text-center"
                  />
                </div>
              </div>

              {/* Partitions */}
              <div className="space-y-3">
                <Label>Número de particiones (n): {partitions[0]}</Label>
                <Slider
                  value={partitions}
                  onValueChange={setPartitions}
                  max={100}
                  min={4}
                  step={2}
                  className="w-full"
                />
                <div className="flex justify-between text-sm text-gray-500">
                  <span>4</span>
                  <span>100</span>
                </div>
              </div>

              {/* Method */}
              <div className="space-y-2">
                <Label>Método de integración</Label>
                <Select value={method} onValueChange={setMethod}>
                  <SelectTrigger>
                    <SelectValue />
                  </SelectTrigger>
                  <SelectContent>
                    <SelectItem value="trapecio">Regla del Trapecio</SelectItem>
                    <SelectItem value="integral_definida">Integral Definida</SelectItem>
                  </SelectContent>
                </Select>
              </div>

              {/* Calculate Button */}
              <Button
                onClick={calculateIntegral}
                disabled={loading}
                className="w-full bg-blue-600 hover:bg-blue-700 text-white py-3 text-lg"
              >
                {loading ? (
                  <div className="flex items-center gap-2">
                    <div className="animate-spin rounded-full h-4 w-4 border-b-2 border-white"></div>
                    Calculando...
                  </div>
                ) : (
                  <div className="flex items-center gap-2">
                    <Play className="h-5 w-5" />
                    Calcular Integral
                  </div>
                )}
              </Button>

              {error && <div className="p-3 bg-red-100 border border-red-300 rounded-lg text-red-700">{error}</div>}
            </CardContent>
          </Card>

          {/* Results Panel - takes 2 columns */}
          <Card className="shadow-lg xl:col-span-2">
            <CardHeader>
              <CardTitle>Resultados y Visualización</CardTitle>
            </CardHeader>
            <CardContent>
              {result ? (
                <div className="space-y-6">
                  {/* Formula Display */}
                  <div className="bg-gray-50 p-4 rounded-lg">
                    <h3 className="font-semibold mb-2">Fórmula:</h3>
                    <div className="text-center" dangerouslySetInnerHTML={renderLatex(result.latexFormula)} />
                  </div>

                  {/* Area Result */}
                  <div className="bg-blue-50 p-4 rounded-lg text-center">
                    <h3 className="font-semibold text-blue-800 mb-2">Área calculada:</h3>
                    <div className="text-3xl font-bold text-blue-600">{result.area.toFixed(6)}</div>
                    <p className="text-sm text-blue-600 mt-1">Método: {result.method}</p>
                  </div>

                  {/* Chart */}
                  <div className="bg-white border rounded-lg p-4">
                    <h3 className="font-semibold mb-3">Visualización Gráfica:</h3>
                    <canvas
                      ref={canvasRef}
                      width={800}
                      height={500}
                      className="w-full h-[500px] border rounded-lg shadow-lg bg-white"
                      style={{ maxWidth: "100%" }}
                    />
                  </div>

                  {/* Partial Areas */}
                  {result.partialAreas.length > 0 && (
                    <div className="bg-green-50 p-4 rounded-lg">
                      <h3 className="font-semibold text-green-800 mb-2">Áreas Parciales:</h3>
                      <div className="grid grid-cols-4 gap-2 text-sm">
                        {result.partialAreas.slice(0, 8).map((area, index) => (
                          <div key={index} className="bg-white p-2 rounded text-center">
                            <div className="font-mono text-xs text-gray-600">A_{index + 1}</div>
                            <div className="font-semibold">{area.toFixed(4)}</div>
                          </div>
                        ))}
                      </div>
                      {result.partialAreas.length > 8 && (
                        <p className="text-sm text-gray-600 mt-2">... y {result.partialAreas.length - 8} áreas más</p>
                      )}
                    </div>
                  )}
                </div>
              ) : (
                <div className="text-center py-12 text-gray-500">
                  <Calculator className="h-16 w-16 mx-auto mb-4 opacity-50" />
                  <p className="text-lg">Ingresa una función y presiona "Calcular Integral"</p>
                  <p className="text-sm mt-2">Los resultados y la gráfica aparecerán aquí</p>
                </div>
              )}
            </CardContent>
          </Card>
        </div>

        {/* Instructions */}
        <Card className="mt-8 shadow-lg">
          <CardHeader>
            <CardTitle>Instrucciones de Uso</CardTitle>
          </CardHeader>
          <CardContent>
            <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
              <div>
                <h4 className="font-semibold mb-2">Funciones Soportadas:</h4>
                <ul className="text-sm space-y-1 text-gray-600">
                  <li>• Polinomios: x^2, x^3, 2*x + 1</li>
                  <li>• Trigonométricas: sin(x), cos(x), tan(x)</li>
                  <li>• Exponenciales: e^x, 2^x</li>
                  <li>• Logarítmicas: log(x), ln(x)</li>
                  <li>• Combinaciones: x^2 + sin(x)</li>
                </ul>
              </div>
              <div>
                <h4 className="font-semibold mb-2">Métodos Disponibles:</h4>
                <ul className="text-sm space-y-1 text-gray-600">
                  <li>
                    • <strong>Regla del Trapecio:</strong> Aproximación usando trapecios
                  </li>
                  <li>
                    • <strong>Integral Definida:</strong> Cálculo analítico cuando es posible
                  </li>
                </ul>
              </div>
            </div>
          </CardContent>
        </Card>
      </div>
    </div>
  )
}
