import { type NextRequest, NextResponse } from "next/server"

interface CalculateRequest {
  function: string
  lowerLimit: number
  upperLimit: number
  partitions: number
  method: string
}

interface CalculationResult {
  area: number
  points: Array<{ x: number; y: number }>
  partialAreas: number[]
  latexFormula: string
  method: string
}

// Improved function evaluator with better error handling
function evaluateFunction(func: string, x: number): number {
  try {
    // Clean and prepare the function string
    let expression = func
      .toLowerCase()
      .replace(/\s+/g, "") // Remove all spaces
      .replace(/\^/g, "**") // Replace ^ with **
      .replace(/sin/g, "Math.sin")
      .replace(/cos/g, "Math.cos")
      .replace(/tan/g, "Math.tan")
      .replace(/log/g, "Math.log10")
      .replace(/ln/g, "Math.log")
      .replace(/sqrt/g, "Math.sqrt")
      .replace(/abs/g, "Math.abs")
      .replace(/pi/g, "Math.PI")
      .replace(/e(?!\d)/g, "Math.E") // Replace e but not in numbers
      .replace(/(\d)([a-z])/g, "$1*$2") // Add multiplication: 2x -> 2*x
      .replace(/([a-z])(\d)/g, "$1*$2") // Add multiplication: x2 -> x*2
      .replace(/\)(\d)/g, ")*$1") // Add multiplication: )2 -> )*2
      .replace(/(\d)\(/g, "$1*(") // Add multiplication: 2( -> 2*(

    // Replace x with the actual value, wrapped in parentheses for safety
    expression = expression.replace(/x/g, `(${x})`)

    // Use Function constructor instead of eval for better security
    const result = Function(`"use strict"; return (${expression})`)()

    // Check if result is valid
    if (isNaN(result) || !isFinite(result)) {
      throw new Error(`Invalid result for x=${x}: ${result}`)
    }

    return result
  } catch (error) {
    console.error(`Error evaluating function "${func}" at x=${x}:`, error)
    throw new Error(`Cannot evaluate function at x=${x}`)
  }
}

// Trapezoidal rule implementation
function trapezoidalRule(func: string, a: number, b: number, n: number): CalculationResult {
  const h = (b - a) / n
  const points: Array<{ x: number; y: number }> = []
  const partialAreas: number[] = []

  // Generate points for plotting
  for (let i = 0; i <= n * 2; i++) {
    const x = a + (i * (b - a)) / (n * 2)
    const y = evaluateFunction(func, x)
    points.push({ x, y })
  }

  // Calculate area using trapezoidal rule
  let area = 0
  for (let i = 0; i < n; i++) {
    const x1 = a + i * h
    const x2 = a + (i + 1) * h
    const y1 = evaluateFunction(func, x1)
    const y2 = evaluateFunction(func, x2)

    const partialArea = (h / 2) * (y1 + y2)
    partialAreas.push(partialArea)
    area += partialArea
  }

  // Generate LaTeX formula
  const latexFormula = `\\int_{${a}}^{${b}} ${func.replace(/\*/g, "").replace(/\^/g, "^")} \\, dx \\approx \\frac{h}{2}\\sum_{i=0}^{${n - 1}}[f(x_i) + f(x_{i+1})] = ${area.toFixed(6)}`

  return {
    area,
    points,
    partialAreas,
    latexFormula,
    method: "Regla del Trapecio",
  }
}

// Definite integral (simplified - using trapezoidal with high precision)
function definiteIntegral(func: string, a: number, b: number, n: number): CalculationResult {
  // Use high precision trapezoidal rule for "exact" calculation
  const highPrecisionN = Math.max(n * 10, 1000)
  const result = trapezoidalRule(func, a, b, highPrecisionN)

  // Generate points for original partition count for visualization
  const h = (b - a) / n
  const points: Array<{ x: number; y: number }> = []
  const partialAreas: number[] = []

  for (let i = 0; i <= n * 2; i++) {
    const x = a + (i * (b - a)) / (n * 2)
    const y = evaluateFunction(func, x)
    points.push({ x, y })
  }

  // Calculate partial areas for display
  for (let i = 0; i < n; i++) {
    const x1 = a + i * h
    const x2 = a + (i + 1) * h
    const y1 = evaluateFunction(func, x1)
    const y2 = evaluateFunction(func, x2)

    const partialArea = (h / 2) * (y1 + y2)
    partialAreas.push(partialArea)
  }

  const latexFormula = `\\int_{${a}}^{${b}} ${func.replace(/\*/g, "").replace(/\^/g, "^")} \\, dx = ${result.area.toFixed(6)}`

  return {
    area: result.area,
    points,
    partialAreas,
    latexFormula,
    method: "Integral Definida (Alta Precisión)",
  }
}

export async function POST(request: NextRequest) {
  try {
    const body: CalculateRequest = await request.json()
    const { function: func, lowerLimit, upperLimit, partitions, method } = body

    // Validate input
    if (!func || func.trim() === "") {
      return NextResponse.json({ error: "La función no puede estar vacía" }, { status: 400 })
    }

    if (typeof lowerLimit !== "number" || typeof upperLimit !== "number" || !partitions) {
      return NextResponse.json({ error: "Parámetros numéricos inválidos" }, { status: 400 })
    }

    if (lowerLimit >= upperLimit) {
      return NextResponse.json({ error: "El límite inferior debe ser menor que el superior" }, { status: 400 })
    }

    if (partitions < 1 || partitions > 1000) {
      return NextResponse.json({ error: "El número de particiones debe estar entre 1 y 1000" }, { status: 400 })
    }

    // Test function evaluation at a few points to catch errors early
    try {
      evaluateFunction(func, lowerLimit)
      evaluateFunction(func, upperLimit)
      evaluateFunction(func, (lowerLimit + upperLimit) / 2)
    } catch (evalError) {
      return NextResponse.json(
        {
          error: `Error en la función: ${evalError instanceof Error ? evalError.message : "Función inválida"}`,
        },
        { status: 400 },
      )
    }

    let result: CalculationResult

    if (method === "trapecio") {
      result = trapezoidalRule(func, lowerLimit, upperLimit, partitions)
    } else if (method === "integral_definida") {
      result = definiteIntegral(func, lowerLimit, upperLimit, partitions)
    } else {
      return NextResponse.json({ error: "Método no válido. Use 'trapecio' o 'integral_definida'" }, { status: 400 })
    }

    return NextResponse.json(result)
  } catch (error) {
    console.error("Error in calculation API:", error)

    if (error instanceof SyntaxError) {
      return NextResponse.json({ error: "Error de sintaxis en la función matemática" }, { status: 400 })
    }

    return NextResponse.json(
      {
        error: `Error en el cálculo: ${error instanceof Error ? error.message : "Error desconocido"}`,
      },
      { status: 500 },
    )
  }
}
