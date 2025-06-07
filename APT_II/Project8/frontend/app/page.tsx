"use client"

import { useState, useEffect } from "react"
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from "@/components/ui/card"
import { Button } from "@/components/ui/button"
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select"
import { Input } from "@/components/ui/input"
import { Label } from "@/components/ui/label"
import { Alert, AlertDescription } from "@/components/ui/alert"
import { Badge } from "@/components/ui/badge"
import { Separator } from "@/components/ui/separator"
import {
  Calculator,
  ShoppingCart,
  Calendar,
  Receipt,
  RotateCcw,
  Mail,
  Download,
  Send,
  CheckCircle,
  Loader2,
  Utensils,
  Star,
  Clock,
  Zap,
} from "lucide-react"
import { sendInvoiceEmail } from "./actions"

// Menús por día de la semana
const DAILY_MENUS = {
  lunes: {
    name: "McMonday Especial",
    theme: "🍔 Burger Monday",
    color: "from-red-500 to-orange-500",
    bgColor: "bg-red-50",
    borderColor: "border-red-200",
    items: [
      { id: 1, name: "Big Mac Combo", price: 12.99, description: "Big Mac + Papas + Bebida", isCombo: true },
      {
        id: 2,
        name: "Quarter Pounder Combo",
        price: 13.99,
        description: "Quarter Pounder + Papas + Bebida",
        isCombo: true,
      },
      { id: 3, name: "McChicken Deluxe", price: 8.99, description: "Pollo crujiente premium", isCombo: false },
      { id: 4, name: "McNuggets 20 pzs", price: 11.99, description: "20 nuggets dorados", isCombo: false },
    ],
  },
  martes: {
    name: "Taco Tuesday",
    theme: "🌮 Mexican Fusion",
    color: "from-green-500 to-lime-500",
    bgColor: "bg-green-50",
    borderColor: "border-green-200",
    items: [
      { id: 1, name: "McTaco Combo", price: 10.99, description: "3 Tacos + Papas + Bebida", isCombo: true },
      {
        id: 2,
        name: "Quesadilla Grande Combo",
        price: 12.49,
        description: "Quesadilla + Papas + Bebida",
        isCombo: true,
      },
      { id: 3, name: "Nachos Supreme", price: 7.99, description: "Nachos con queso y jalapeños", isCombo: false },
      { id: 4, name: "Burrito Bowl", price: 9.99, description: "Bowl con arroz, frijoles y pollo", isCombo: false },
    ],
  },
  miercoles: {
    name: "Wings Wednesday",
    theme: "🍗 Chicken Wings",
    color: "from-orange-500 to-red-500",
    bgColor: "bg-orange-50",
    borderColor: "border-orange-200",
    items: [
      { id: 1, name: "Buffalo Wings Combo", price: 14.99, description: "12 alitas + Papas + Bebida", isCombo: true },
      { id: 2, name: "BBQ Wings Combo", price: 14.99, description: "12 alitas BBQ + Papas + Bebida", isCombo: true },
      { id: 3, name: "Honey Mustard Wings", price: 11.99, description: "8 alitas con miel y mostaza", isCombo: false },
      { id: 4, name: "Spicy Wings", price: 12.99, description: "10 alitas extra picantes", isCombo: false },
    ],
  },
  jueves: {
    name: "Throwback Thursday",
    theme: "🍕 Pizza & Pasta",
    color: "from-purple-500 to-pink-500",
    bgColor: "bg-purple-50",
    borderColor: "border-purple-200",
    items: [
      {
        id: 1,
        name: "McPizza Personal Combo",
        price: 13.99,
        description: "Pizza personal + Papas + Bebida",
        isCombo: true,
      },
      {
        id: 2,
        name: "Mac & Cheese Deluxe Combo",
        price: 11.99,
        description: "Macarrones premium + Pan + Bebida",
        isCombo: true,
      },
      { id: 3, name: "Spaghetti Bolognese", price: 9.99, description: "Pasta con salsa de carne", isCombo: false },
      { id: 4, name: "Lasagna Slice", price: 8.99, description: "Porción de lasagna casera", isCombo: false },
    ],
  },
  viernes: {
    name: "Fish Friday",
    theme: "🐟 Seafood Special",
    color: "from-blue-500 to-cyan-500",
    bgColor: "bg-blue-50",
    borderColor: "border-blue-200",
    items: [
      { id: 1, name: "Filet-O-Fish Combo", price: 11.99, description: "Filet-O-Fish + Papas + Bebida", isCombo: true },
      {
        id: 2,
        name: "Shrimp Basket Combo",
        price: 15.99,
        description: "Canasta de camarones + Papas + Bebida",
        isCombo: true,
      },
      { id: 3, name: "Fish & Chips", price: 12.99, description: "Pescado empanizado con papas", isCombo: false },
      { id: 4, name: "Salmon Teriyaki", price: 16.99, description: "Salmón glaseado premium", isCombo: false },
    ],
  },
  sabado: {
    name: "Saturday Specials",
    theme: "🥩 Premium Steaks",
    color: "from-gray-700 to-gray-900",
    bgColor: "bg-gray-50",
    borderColor: "border-gray-200",
    items: [
      { id: 1, name: "Angus Deluxe", price: 18.99, description: "Hamburguesa Angus premium", isCombo: false },
      { id: 2, name: "Ribeye Steak", price: 24.99, description: "Corte de ribeye 8oz", isCombo: false },
      { id: 3, name: "Surf & Turf", price: 28.99, description: "Steak + Camarones", isCombo: false },
      { id: 4, name: "Premium Salad", price: 14.99, description: "Ensalada gourmet con proteína", isCombo: false },
    ],
  },
  domingo: {
    name: "Sunday Brunch",
    theme: "🥞 Breakfast All Day",
    color: "from-yellow-500 to-orange-500",
    bgColor: "bg-yellow-50",
    borderColor: "border-yellow-200",
    items: [
      { id: 1, name: "McGriddles Stack", price: 9.99, description: "Pancakes con jarabe", isCombo: false },
      { id: 2, name: "Breakfast Burrito", price: 8.99, description: "Burrito de desayuno", isCombo: false },
      { id: 3, name: "French Toast Deluxe", price: 11.99, description: "Tostadas francesas premium", isCombo: false },
      { id: 4, name: "Eggs Benedict", price: 13.99, description: "Huevos benedictinos", isCombo: false },
    ],
  },
}

interface MenuItem {
  id: number
  name: string
  price: number
  description: string
  isCombo: boolean
}

interface CalculationResult {
  day: string
  selectedItems: MenuItem[]
  subtotal: number
  discountPercentage: number
  discountAmount: number
  totalAfterDiscount: number
  taxAmount: number
  finalTotal: number
  resultText: string
  invoiceNumber: string
  date: string
  comboDiscount: boolean
}

export default function McMenusCalculator() {
  const [selectedDay, setSelectedDay] = useState<string>("")
  const [selectedItems, setSelectedItems] = useState<MenuItem[]>([])
  const [email, setEmail] = useState<string>("")
  const [result, setResult] = useState<CalculationResult | null>(null)
  const [error, setError] = useState<string>("")
  const [isCalculating, setIsCalculating] = useState(false)
  const [isSendingEmail, setIsSendingEmail] = useState(false)
  const [emailSent, setEmailSent] = useState(false)
  const [animateTitle, setAnimateTitle] = useState(false)

  const days = [
    { value: "lunes", label: "Lunes" },
    { value: "martes", label: "Martes" },
    { value: "miercoles", label: "Miércoles" },
    { value: "jueves", label: "Jueves" },
    { value: "viernes", label: "Viernes" },
    { value: "sabado", label: "Sábado" },
    { value: "domingo", label: "Domingo" },
  ]

  // Animación del título
  useEffect(() => {
    const interval = setInterval(() => {
      setAnimateTitle(true)
      setTimeout(() => setAnimateTitle(false), 1000)
    }, 3000)
    return () => clearInterval(interval)
  }, [])

  const currentMenu = selectedDay ? DAILY_MENUS[selectedDay as keyof typeof DAILY_MENUS] : null

  const addItem = (item: MenuItem) => {
    setSelectedItems((prev) => [...prev, { ...item, id: Date.now() + Math.random() }])
  }

  const removeItem = (itemId: number) => {
    setSelectedItems((prev) => prev.filter((item) => item.id !== itemId))
  }

  const validateInput = (day: string, items: MenuItem[], emailValue: string): boolean => {
    if (!day) {
      setError("Por favor, selecciona un día de la semana.")
      return false
    }

    if (items.length === 0) {
      setError("Por favor, selecciona al menos un item del menú.")
      return false
    }

    if (!emailValue || !isValidEmail(emailValue)) {
      setError("Por favor, ingresa un correo electrónico válido.")
      return false
    }

    return true
  }

  const isValidEmail = (email: string): boolean => {
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/
    return emailRegex.test(email)
  }

  const generateInvoiceNumber = (): string => {
    const timestamp = Date.now()
    const random = Math.floor(Math.random() * 1000)
    return `MC-${timestamp}-${random.toString().padStart(3, "0")}`
  }

  const calculateTotal = async () => {
    setError("")
    setEmailSent(false)
    setIsCalculating(true)

    // Animación de carga estilo McDonald's
    await new Promise((resolve) => setTimeout(resolve, 1500))

    if (!validateInput(selectedDay, selectedItems, email)) {
      setIsCalculating(false)
      return
    }

    const subtotal = selectedItems.reduce((sum, item) => sum + item.price, 0)
    let discountPercentage = 0
    let comboDiscount = false

    // Lógica de descuentos
    const hasCombo = selectedItems.some((item) => item.isCombo)
    const isWeekday = ["lunes", "martes", "miercoles", "jueves", "viernes"].includes(selectedDay)

    if (isWeekday && hasCombo) {
      discountPercentage = 0.5 // 50% descuento para combos en días de semana
      comboDiscount = true
    } else if (isWeekday) {
      discountPercentage = 0.25 // 25% descuento general en días de semana
    } else if (hasCombo) {
      discountPercentage = 0.15 // 15% descuento por combo en fin de semana
      comboDiscount = true
    }

    const discountAmount = subtotal * discountPercentage
    const totalAfterDiscount = subtotal - discountAmount
    const taxAmount = totalAfterDiscount * 0.07 // 7% impuesto
    const finalTotal = totalAfterDiscount + taxAmount

    const invoiceNumber = generateInvoiceNumber()
    const currentDate = new Date().toLocaleDateString("es-ES", {
      year: "numeric",
      month: "long",
      day: "numeric",
      hour: "2-digit",
      minute: "2-digit",
    })

    // StringBuilder para la factura
    const resultText = generateInvoiceText({
      invoiceNumber,
      date: currentDate,
      email,
      day: selectedDay,
      items: selectedItems,
      subtotal,
      discountAmount,
      discountPercentage,
      totalAfterDiscount,
      taxAmount,
      finalTotal,
      comboDiscount,
    })

    setResult({
      day: selectedDay,
      selectedItems,
      subtotal,
      discountPercentage,
      discountAmount,
      totalAfterDiscount,
      taxAmount,
      finalTotal,
      resultText,
      invoiceNumber,
      date: currentDate,
      comboDiscount,
    })

    setIsCalculating(false)
  }

  const generateInvoiceText = (data: any): string => {
    let text = ""
    text += "=".repeat(60) + "\n"
    text += "                    🍟 McMENUS FACTURA 🍟\n"
    text += "                   RESTAURANTE McDONALD'S\n"
    text += "=".repeat(60) + "\n\n"
    text += `Número de Factura: ${data.invoiceNumber}\n`
    text += `Fecha y Hora: ${data.date}\n`
    text += `Cliente: ${data.email}\n`
    text += `Día especial: ${DAILY_MENUS[data.day as keyof typeof DAILY_MENUS].name}\n\n`
    text += "ITEMS ORDENADOS:\n"
    text += "-".repeat(40) + "\n"

    data.items.forEach((item: MenuItem, index: number) => {
      text += `${index + 1}. ${item.name}${item.isCombo ? " 🍟" : ""}\n`
      text += `   ${item.description}\n`
      text += `   $${item.price.toFixed(2)}\n\n`
    })

    text += "RESUMEN FINANCIERO:\n"
    text += "-".repeat(40) + "\n"
    text += `Subtotal:                 $${data.subtotal.toFixed(2)}\n`
    text += `Descuento aplicado:       $${data.discountAmount.toFixed(2)} (${(data.discountPercentage * 100).toFixed(0)}%)\n`
    text += `Total con descuento:      $${data.totalAfterDiscount.toFixed(2)}\n`
    text += `Impuesto (7%):            $${data.taxAmount.toFixed(2)}\n`
    text += "-".repeat(40) + "\n"
    text += `TOTAL A PAGAR:            $${data.finalTotal.toFixed(2)}\n\n`

    if (data.comboDiscount) {
      text += "🎉 ¡DESCUENTO ESPECIAL POR COMBO DEL DÍA!\n"
    }

    text += "¡Gracias por elegir McDonald's! 🍔✨\n"
    text += "=".repeat(60)

    return text
  }

  const sendEmail = async () => {
    if (!result) return
    setIsSendingEmail(true)

    try {
      const response = await sendInvoiceEmail({
        email,
        invoiceNumber: result.invoiceNumber,
        date: result.date,
        day: result.day,
        subtotal: result.subtotal,
        discountAmount: result.discountAmount,
        discountPercentage: result.discountPercentage,
        totalAfterDiscount: result.totalAfterDiscount,
        taxAmount: result.taxAmount,
        finalTotal: result.finalTotal,
        resultText: result.resultText,
      })

      if (response.success) {
        setEmailSent(true)
      } else {
        setError(response.message || "Error al enviar el correo")
      }
    } catch (error) {
      setError("Error al enviar el correo electrónico")
    } finally {
      setIsSendingEmail(false)
    }
  }

  const clearResults = () => {
    setResult(null)
    setSelectedDay("")
    setSelectedItems([])
    setEmail("")
    setError("")
    setEmailSent(false)
  }

  const formatCurrency = (amount: number): string => {
    return amount.toFixed(2)
  }

  return (
    <div className="min-h-screen bg-gradient-to-br from-red-50 via-yellow-50 to-orange-50 p-4">
      <div className="max-w-6xl mx-auto space-y-6">
        {/* Header estilo McDonald's */}
        <div className="text-center space-y-4">
          <div className={`transition-all duration-1000 ${animateTitle ? "scale-110 rotate-2" : "scale-100 rotate-0"}`}>
            <h1 className="text-6xl font-black bg-gradient-to-r from-red-600 via-yellow-500 to-red-600 bg-clip-text text-transparent drop-shadow-lg">
              🍟 McMENUS 🍔
            </h1>
            <div className="flex items-center justify-center gap-2 mt-2">
              <div className="w-8 h-1 bg-red-500 rounded-full animate-pulse"></div>
              <div className="w-8 h-1 bg-yellow-500 rounded-full animate-pulse delay-100"></div>
              <div className="w-8 h-1 bg-red-500 rounded-full animate-pulse delay-200"></div>
            </div>
          </div>
          <p className="text-xl text-gray-700 font-semibold">
            ¡Menús especiales cada día con descuentos increíbles!
            <span className="animate-bounce inline-block ml-2">🎉</span>
          </p>
        </div>

        {/* Selector de día */}
        <Card className="shadow-xl border-2 border-red-200">
          <CardHeader className="bg-gradient-to-r from-red-500 to-yellow-500 text-white">
            <CardTitle className="flex items-center gap-2 text-2xl">
              <Calendar className="h-6 w-6" />
              Selecciona tu día especial
            </CardTitle>
            <CardDescription className="text-red-100">
              Cada día tiene un menú temático único con ofertas especiales
            </CardDescription>
          </CardHeader>
          <CardContent className="pt-6">
            <div className="grid md:grid-cols-2 gap-6">
              <div className="space-y-2">
                <Label htmlFor="day-select" className="text-lg font-semibold">
                  Día de la semana
                </Label>
                <Select value={selectedDay} onValueChange={setSelectedDay}>
                  <SelectTrigger id="day-select" className="h-12 text-lg">
                    <SelectValue placeholder="🗓️ Elige tu día favorito" />
                  </SelectTrigger>
                  <SelectContent>
                    {days.map((day) => (
                      <SelectItem key={day.value} value={day.value} className="text-lg">
                        <div className="flex items-center gap-3">
                          <span>{DAILY_MENUS[day.value as keyof typeof DAILY_MENUS].theme}</span>
                          <span className="font-semibold">{day.label}</span>
                          {["lunes", "martes", "miercoles", "jueves", "viernes"].includes(day.value) && (
                            <Badge className="bg-green-100 text-green-700 animate-pulse">¡DESCUENTO!</Badge>
                          )}
                        </div>
                      </SelectItem>
                    ))}
                  </SelectContent>
                </Select>
              </div>

              <div className="space-y-2">
                <Label htmlFor="email-input" className="text-lg font-semibold">
                  Tu correo electrónico
                </Label>
                <div className="relative">
                  <Mail className="absolute left-3 top-1/2 transform -translate-y-1/2 h-5 w-5 text-gray-400" />
                  <Input
                    id="email-input"
                    type="email"
                    placeholder="tu@email.com"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                    className="pl-12 h-12 text-lg"
                  />
                </div>
              </div>
            </div>
          </CardContent>
        </Card>

        {/* Menú del día */}
        {currentMenu && (
          <Card className={`shadow-xl border-2 ${currentMenu.borderColor} ${currentMenu.bgColor} animate-fadeIn`}>
            <CardHeader className={`bg-gradient-to-r ${currentMenu.color} text-white`}>
              <CardTitle className="text-3xl font-bold flex items-center gap-3">
                <Utensils className="h-8 w-8" />
                {currentMenu.name}
              </CardTitle>
              <CardDescription className="text-white/90 text-lg">
                {currentMenu.theme} - ¡Menú especial de hoy!
              </CardDescription>
            </CardHeader>
            <CardContent className="pt-6">
              <div className="grid md:grid-cols-2 gap-4">
                {currentMenu.items.map((item) => (
                  <Card
                    key={item.id}
                    className="hover:shadow-lg transition-all duration-300 hover:scale-105 cursor-pointer border-2 hover:border-yellow-400"
                  >
                    <CardContent className="p-4">
                      <div className="flex justify-between items-start mb-2">
                        <h4 className="font-bold text-lg flex items-center gap-2">
                          {item.name}
                          {item.isCombo && (
                            <Badge className="bg-red-500 text-white animate-pulse">
                              <Star className="h-3 w-3 mr-1" />
                              COMBO
                            </Badge>
                          )}
                        </h4>
                        <span className="text-2xl font-black text-green-600">${item.price.toFixed(2)}</span>
                      </div>
                      <p className="text-gray-600 mb-3">{item.description}</p>
                      <Button
                        onClick={() => addItem(item)}
                        className="w-full bg-gradient-to-r from-red-500 to-yellow-500 hover:from-red-600 hover:to-yellow-600 text-white font-bold"
                      >
                        <ShoppingCart className="h-4 w-4 mr-2" />
                        Agregar al pedido
                      </Button>
                    </CardContent>
                  </Card>
                ))}
              </div>
            </CardContent>
          </Card>
        )}

        {/* Carrito de compras */}
        {selectedItems.length > 0 && (
          <Card className="shadow-xl border-2 border-yellow-400 bg-yellow-50">
            <CardHeader className="bg-gradient-to-r from-yellow-500 to-orange-500 text-white">
              <CardTitle className="flex items-center gap-2 text-2xl">
                <ShoppingCart className="h-6 w-6" />
                Tu pedido ({selectedItems.length} items)
              </CardTitle>
            </CardHeader>
            <CardContent className="pt-6">
              <div className="space-y-3">
                {selectedItems.map((item, index) => (
                  <div
                    key={`${item.id}-${index}`}
                    className="flex justify-between items-center p-3 bg-white rounded-lg border"
                  >
                    <div>
                      <span className="font-semibold">{item.name}</span>
                      {item.isCombo && <Badge className="ml-2 bg-red-100 text-red-700">COMBO</Badge>}
                    </div>
                    <div className="flex items-center gap-3">
                      <span className="font-bold text-green-600">${item.price.toFixed(2)}</span>
                      <Button onClick={() => removeItem(item.id)} variant="destructive" size="sm">
                        Quitar
                      </Button>
                    </div>
                  </div>
                ))}
              </div>

              <Separator className="my-4" />

              <div className="flex justify-between items-center text-xl font-bold">
                <span>Subtotal:</span>
                <span className="text-green-600">
                  ${selectedItems.reduce((sum, item) => sum + item.price, 0).toFixed(2)}
                </span>
              </div>

              {error && (
                <Alert variant="destructive" className="mt-4">
                  <AlertDescription>{error}</AlertDescription>
                </Alert>
              )}

              {emailSent && (
                <Alert className="border-green-200 bg-green-50 mt-4">
                  <CheckCircle className="h-4 w-4 text-green-600" />
                  <AlertDescription className="text-green-700">
                    ¡Factura enviada exitosamente a {email}! 🎉
                  </AlertDescription>
                </Alert>
              )}

              <div className="flex gap-3 mt-6">
                <Button
                  onClick={calculateTotal}
                  className="flex-1 bg-gradient-to-r from-red-600 to-yellow-600 hover:from-red-700 hover:to-yellow-700 text-white font-bold text-lg h-12"
                  disabled={isCalculating}
                >
                  {isCalculating ? (
                    <>
                      <Loader2 className="h-5 w-5 mr-2 animate-spin" />
                      Procesando...
                    </>
                  ) : (
                    <>
                      <Calculator className="h-5 w-5 mr-2" />
                      ¡Calcular mi pedido!
                    </>
                  )}
                </Button>

                <Button onClick={clearResults} variant="outline" size="lg" className="px-6">
                  <RotateCcw className="h-4 w-4 mr-2" />
                  Limpiar
                </Button>
              </div>
            </CardContent>
          </Card>
        )}

        {/* Resultados */}
        {result && (
          <Card className="shadow-xl border-2 border-green-400 bg-green-50 animate-slideUp">
            <CardHeader className="bg-gradient-to-r from-green-600 to-blue-600 text-white">
              <CardTitle className="flex items-center gap-2 text-2xl">
                <Receipt className="h-6 w-6" />🍟 Factura McMENUS #{result.invoiceNumber}
              </CardTitle>
              <CardDescription className="text-green-100">
                {result.date} - {email}
              </CardDescription>
            </CardHeader>
            <CardContent className="pt-6 space-y-6">
              {/* Resumen visual */}
              <div className="grid grid-cols-2 md:grid-cols-4 gap-4">
                <div className="text-center p-4 bg-white rounded-lg border-2 border-gray-200">
                  <p className="text-sm text-gray-600">Subtotal</p>
                  <p className="text-2xl font-bold text-gray-900">${formatCurrency(result.subtotal)}</p>
                </div>

                <div className="text-center p-4 bg-white rounded-lg border-2 border-green-200">
                  <p className="text-sm text-green-600">Descuento</p>
                  <p className="text-2xl font-bold text-green-700">-${formatCurrency(result.discountAmount)}</p>
                  <p className="text-xs text-green-600">({(result.discountPercentage * 100).toFixed(0)}%)</p>
                </div>

                <div className="text-center p-4 bg-white rounded-lg border-2 border-blue-200">
                  <p className="text-sm text-blue-600">Impuesto</p>
                  <p className="text-2xl font-bold text-blue-700">+${formatCurrency(result.taxAmount)}</p>
                  <p className="text-xs text-blue-600">(7%)</p>
                </div>

                <div className="text-center p-4 bg-gradient-to-r from-red-500 to-yellow-500 text-white rounded-lg border-2 border-red-300">
                  <p className="text-sm">Total Final</p>
                  <p className="text-3xl font-black">${formatCurrency(result.finalTotal)}</p>
                </div>
              </div>

              {/* Botones de acción */}
              <div className="flex flex-col sm:flex-row gap-3">
                <Button
                  onClick={sendEmail}
                  className="flex-1 bg-gradient-to-r from-green-600 to-emerald-600 hover:from-green-700 hover:to-emerald-700 text-lg h-12"
                  disabled={isSendingEmail}
                >
                  {isSendingEmail ? (
                    <>
                      <Loader2 className="h-5 w-5 mr-2 animate-spin" />
                      Enviando...
                    </>
                  ) : (
                    <>
                      <Send className="h-5 w-5 mr-2" />📧 Enviar Factura
                    </>
                  )}
                </Button>

                <Button
                  onClick={() => {
                    const blob = new Blob([result.resultText], { type: "text/plain" })
                    const url = window.URL.createObjectURL(blob)
                    const a = document.createElement("a")
                    a.href = url
                    a.download = `McMENUS_${result.invoiceNumber}.txt`
                    document.body.appendChild(a)
                    a.click()
                    document.body.removeChild(a)
                    window.URL.revokeObjectURL(url)
                  }}
                  variant="outline"
                  className="flex-1 text-lg h-12"
                >
                  <Download className="h-5 w-5 mr-2" />📄 Descargar
                </Button>
              </div>

              {/* Vista previa de la factura */}
              <div className="space-y-2">
                <Label className="text-lg font-semibold">Vista previa de tu factura McMENUS</Label>
                <div className="bg-gray-900 text-green-400 p-4 rounded-lg font-mono text-sm overflow-x-auto">
                  <pre className="whitespace-pre-wrap">{result.resultText}</pre>
                </div>
              </div>

              {/* Mensaje especial */}
              {result.comboDiscount ? (
                <Alert className="border-yellow-400 bg-yellow-50">
                  <Star className="h-4 w-4 text-yellow-600" />
                  <AlertDescription className="text-yellow-800 font-semibold">
                    🎉 ¡DESCUENTO ESPECIAL POR COMBO DEL DÍA! Ahorraste $
                    {formatCurrency(result.subtotal - result.finalTotal)}
                  </AlertDescription>
                </Alert>
              ) : result.discountPercentage > 0 ? (
                <Alert className="border-green-400 bg-green-50">
                  <Zap className="h-4 w-4 text-green-600" />
                  <AlertDescription className="text-green-800">
                    💰 ¡Genial! Ahorraste ${formatCurrency(result.discountAmount)} con nuestros descuentos especiales.
                  </AlertDescription>
                </Alert>
              ) : (
                <Alert className="border-orange-400 bg-orange-50">
                  <Clock className="h-4 w-4 text-orange-600" />
                  <AlertDescription className="text-orange-800">
                    💡 ¡Visítanos de lunes a viernes y ordena combos para obtener hasta 50% de descuento!
                  </AlertDescription>
                </Alert>
              )}
            </CardContent>
          </Card>
        )}

        {/* Footer */}
        <Card className="bg-gradient-to-r from-red-600 to-yellow-600 text-white">
          <CardContent className="pt-6 text-center">
            <div className="space-y-2">
              <p className="text-lg font-bold">🍟 ¡I'm Lovin' It! 🍔</p>
              <p className="text-sm opacity-90">McMENUS - Menús especiales cada día con los mejores descuentos</p>
              <p className="text-xs opacity-75">* Descuentos aplicables según día y tipo de combo seleccionado</p>
            </div>
          </CardContent>
        </Card>
      </div>

      <style jsx>{`
        @keyframes fadeIn {
          from { opacity: 0; transform: translateY(20px); }
          to { opacity: 1; transform: translateY(0); }
        }
        
        @keyframes slideUp {
          from { opacity: 0; transform: translateY(30px); }
          to { opacity: 1; transform: translateY(0); }
        }
        
        .animate-fadeIn {
          animation: fadeIn 0.6s ease-out;
        }
        
        .animate-slideUp {
          animation: slideUp 0.8s ease-out;
        }
      `}</style>
    </div>
  )
}
