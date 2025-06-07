// Simulación de StringBuilder de Java
class StringBuilder {
  constructor() {
    this.buffer = []
  }

  append(text) {
    this.buffer.push(text)
    return this
  }

  appendLine(text = "") {
    this.buffer.push(text + "\n")
    return this
  }

  toString() {
    return this.buffer.join("")
  }

  clear() {
    this.buffer = []
    return this
  }
}

// Variables globales
const form = document.getElementById("discountForm")
const daySelect = document.getElementById("daySelect")
const subtotalInput = document.getElementById("subtotalInput")
const errorMessage = document.getElementById("errorMessage")
const resultsContainer = document.getElementById("resultsContainer")
const resultsOutput = document.getElementById("resultsOutput")
const clearBtn = document.getElementById("clearBtn")

// Event listeners
form.addEventListener("submit", handleFormSubmit)
clearBtn.addEventListener("click", clearResults)

// Función principal que maneja el envío del formulario
function handleFormSubmit(event) {
  event.preventDefault()

  // Limpiar mensajes de error previos
  hideError()

  // Obtener valores del formulario
  const selectedDay = daySelect.value.trim()
  const subtotalValue = Number.parseFloat(subtotalInput.value)

  // Validar entrada
  if (!validateInput(selectedDay, subtotalValue)) {
    return
  }

  // Calcular descuentos e impuestos
  calculateAndDisplayResults(selectedDay, subtotalValue)
}

// Función de validación
function validateInput(day, subtotal) {
  if (!day) {
    showError("Por favor, selecciona un día de la semana.")
    return false
  }

  if (isNaN(subtotal) || subtotal < 0) {
    showError("Por favor, ingresa un subtotal válido (mayor o igual a 0).")
    return false
  }

  if (subtotal === 0) {
    showError("El subtotal debe ser mayor a 0.")
    return false
  }

  return true
}

// Función principal de cálculo (simula el switch de Java)
function calculateAndDisplayResults(day, subtotal) {
  const sb = new StringBuilder()
  let discountPercentage = 0
  let discountAmount = 0
  let totalAfterDiscount = 0
  let taxAmount = 0
  let finalTotal = 0

  // Constantes
  const TAX_RATE = 0.07 // 7%
  const WEEKDAY_DISCOUNT = 0.5 // 50%

  // Switch para determinar descuento según el día (simula switch de Java)
  switch (day.toLowerCase()) {
    case "lunes":
    case "martes":
    case "miercoles":
    case "jueves":
    case "viernes":
      discountPercentage = WEEKDAY_DISCOUNT
      break
    case "sabado":
    case "domingo":
      discountPercentage = 0
      break
    default:
      showError("Día no válido.")
      return
  }

  // Cálculos
  discountAmount = subtotal * discountPercentage
  totalAfterDiscount = subtotal - discountAmount
  taxAmount = totalAfterDiscount * TAX_RATE
  finalTotal = totalAfterDiscount + taxAmount

  // Construcción del resultado usando StringBuilder (simula StringBuilder de Java)
  sb.appendLine("=".repeat(50))
    .appendLine("           RESUMEN DE COMPRA")
    .appendLine("=".repeat(50))
    .appendLine()
    .appendLine(`Día seleccionado: ${capitalizeFirstLetter(day)}`)
    .appendLine()
    .appendLine("DETALLES FINANCIEROS:")
    .appendLine("-".repeat(30))
    .appendLine(`Subtotal original:     $${formatCurrency(subtotal)}`)
    .appendLine(`Descuento aplicado:    $${formatCurrency(discountAmount)} (${(discountPercentage * 100).toFixed(0)}%)`)
    .appendLine(`Total con descuento:   $${formatCurrency(totalAfterDiscount)}`)
    .appendLine(`Impuesto (7%):         $${formatCurrency(taxAmount)}`)
    .appendLine("-".repeat(30))
    .appendLine(`TOTAL A PAGAR:         $${formatCurrency(finalTotal)}`)
    .appendLine()
    .appendLine("INFORMACIÓN ADICIONAL:")
    .appendLine("-".repeat(30))

  // Mensaje adicional según el día
  if (discountPercentage > 0) {
    const savings = subtotal - finalTotal
    sb.appendLine(`¡Felicidades! Ahorraste $${formatCurrency(savings)} comprando en día de semana.`).appendLine(
      "Los descuentos de lunes a viernes son del 50%.",
    )
  } else {
    sb.appendLine("No hay descuento disponible los fines de semana.").appendLine(
      "¡Visítanos de lunes a viernes para obtener 50% de descuento!",
    )
  }

  sb.appendLine().appendLine("Gracias por tu compra. 🛒✨").appendLine("=".repeat(50))

  // Mostrar resultados
  displayResults(sb.toString())
}

// Función para mostrar los resultados
function displayResults(resultText) {
  resultsOutput.textContent = resultText
  resultsContainer.style.display = "block"

  // Scroll suave hacia los resultados
  resultsContainer.scrollIntoView({
    behavior: "smooth",
    block: "start",
  })
}

// Función para limpiar resultados
function clearResults() {
  resultsContainer.style.display = "none"
  form.reset()
  hideError()

  // Scroll hacia arriba
  window.scrollTo({
    top: 0,
    behavior: "smooth",
  })
}

// Funciones de utilidad
function showError(message) {
  errorMessage.textContent = message
  errorMessage.classList.add("show")
}

function hideError() {
  errorMessage.classList.remove("show")
  errorMessage.textContent = ""
}

function formatCurrency(amount) {
  return amount.toFixed(2)
}

function capitalizeFirstLetter(string) {
  return string.charAt(0).toUpperCase() + string.slice(1).toLowerCase()
}

// Validación en tiempo real para el campo de subtotal
subtotalInput.addEventListener("input", function () {
  const value = Number.parseFloat(this.value)
  if (this.value && (isNaN(value) || value < 0)) {
    this.style.borderColor = "#dc3545"
  } else {
    this.style.borderColor = "#e1e5e9"
  }
})

// Efecto visual al seleccionar día
daySelect.addEventListener("change", function () {
  if (this.value) {
    this.style.borderColor = "#28a745"
  } else {
    this.style.borderColor = "#e1e5e9"
  }
})
