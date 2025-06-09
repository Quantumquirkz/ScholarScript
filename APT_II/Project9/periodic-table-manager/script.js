document.addEventListener("DOMContentLoaded", () => {
  // DOM Elements
  const elementCountForm = document.getElementById("element-count-form")
  const elementsForm = document.getElementById("elements-form")
  const elementsContainer = document.getElementById("elements-container")
  const resultsSection = document.getElementById("results-section")
  const elementsTableBody = document.getElementById("elements-table-body")
  const maxAtomicNumberCard = document.getElementById("max-atomic-number")
  const maxAtomicWeightCard = document.getElementById("max-atomic-weight")
  const resetBtn = document.getElementById("reset-btn")
  const clearDataBtn = document.getElementById("clear-data-btn")
  const exportBtn = document.getElementById("export-btn")
  const themeToggleBtn = document.getElementById("theme-toggle-btn")
  const loadingIndicator = document.getElementById("loading")
  const notification = document.getElementById("notification")

  // API Base URL - Change this to your Java backend URL
  const API_BASE_URL = "http://localhost:8080"

  // Check for saved theme preference
  if (localStorage.getItem("theme") === "dark") {
    document.body.classList.add("dark-theme")
  }

  // Theme toggle functionality
  themeToggleBtn.addEventListener("click", () => {
    document.body.classList.toggle("dark-theme")
    localStorage.setItem("theme", document.body.classList.contains("dark-theme") ? "dark" : "light")
  })

  // Handle element count form submission
  elementCountForm.addEventListener("submit", (e) => {
    e.preventDefault()
    const count = Number.parseInt(document.getElementById("element-count").value)

    if (count < 1 || count > 118) {
      showNotification("Por favor ingrese un número entre 1 y 118", "error")
      return
    }

    generateElementForms(count)
    elementCountForm.classList.add("hidden")
    elementsForm.classList.remove("hidden")
  })

  // Generate element input forms based on count
  function generateElementForms(count) {
    elementsContainer.innerHTML = ""

    for (let i = 0; i < count; i++) {
      const elementForm = document.createElement("div")
      elementForm.className = "element-form"
      elementForm.innerHTML = `
                <h3>Elemento #${i + 1}</h3>
                <div class="element-form-fields">
                    <div class="form-group">
                        <label for="nombre-${i}">Nombre:</label>
                        <input type="text" id="nombre-${i}" name="nombre-${i}" required pattern="[A-Za-zÁáÉéÍíÓóÚúÑñ\\s]+" title="Solo letras y espacios">
                        <div class="error" id="nombre-error-${i}"></div>
                    </div>
                    <div class="form-group">
                        <label for="simbolo-${i}">Símbolo:</label>
                        <input type="text" id="simbolo-${i}" name="simbolo-${i}" required maxlength="3" pattern="[A-Z][a-z]{0,2}" title="Primera letra mayúscula, máximo 3 caracteres">
                        <div class="error" id="simbolo-error-${i}"></div>
                    </div>
                    <div class="form-group">
                        <label for="numeroAtomico-${i}">Número Atómico:</label>
                        <input type="number" id="numeroAtomico-${i}" name="numeroAtomico-${i}" required min="1" step="1">
                        <div class="error" id="numeroAtomico-error-${i}"></div>
                    </div>
                    <div class="form-group">
                        <label for="pesoAtomico-${i}">Peso Atómico:</label>
                        <input type="number" id="pesoAtomico-${i}" name="pesoAtomico-${i}" required min="0.001" step="0.001">
                        <div class="error" id="pesoAtomico-error-${i}"></div>
                    </div>
                </div>
            `
      elementsContainer.appendChild(elementForm)

      // Add real-time validation
      setupValidation(i)
    }
  }

  // Setup validation for each element form
  function setupValidation(index) {
    const nombreInput = document.getElementById(`nombre-${index}`)
    const simboloInput = document.getElementById(`simbolo-${index}`)
    const numeroAtomicoInput = document.getElementById(`numeroAtomico-${index}`)
    const pesoAtomicoInput = document.getElementById(`pesoAtomico-${index}`)

    const nombreError = document.getElementById(`nombre-error-${index}`)
    const simboloError = document.getElementById(`simbolo-error-${index}`)
    const numeroAtomicoError = document.getElementById(`numeroAtomico-error-${index}`)
    const pesoAtomicoError = document.getElementById(`pesoAtomico-error-${index}`)

    nombreInput.addEventListener("input", () => {
      if (!nombreInput.validity.valid) {
        nombreError.textContent = "El nombre debe contener solo letras y espacios"
      } else {
        nombreError.textContent = ""
      }
    })

    simboloInput.addEventListener("input", () => {
      if (!simboloInput.validity.valid) {
        simboloError.textContent = "El símbolo debe comenzar con mayúscula y tener máximo 3 caracteres"
      } else {
        simboloError.textContent = ""
      }
    })

    numeroAtomicoInput.addEventListener("input", () => {
      if (numeroAtomicoInput.value <= 0) {
        numeroAtomicoError.textContent = "El número atómico debe ser un entero positivo"
      } else {
        numeroAtomicoError.textContent = ""
      }
    })

    pesoAtomicoInput.addEventListener("input", () => {
      if (pesoAtomicoInput.value <= 0) {
        pesoAtomicoError.textContent = "El peso atómico debe ser un número positivo"
      } else {
        pesoAtomicoError.textContent = ""
      }
    })
  }

  // Handle elements form submission
  elementsForm.addEventListener("submit", async (e) => {
    e.preventDefault()

    if (!validateAllElements()) {
      showNotification("Por favor corrija los errores en el formulario", "error")
      return
    }

    const elements = collectElementsData()

    try {
      showLoading()
      await saveElements(elements)
      await fetchAndDisplayElements()
      elementsForm.classList.add("hidden")
      resultsSection.classList.remove("hidden")
      showNotification("Elementos guardados correctamente", "success")
    } catch (error) {
      console.error("Error:", error)
      showNotification("Error al guardar los elementos", "error")
    } finally {
      hideLoading()
    }
  })

  // Validate all element inputs
  function validateAllElements() {
    const count = document.getElementById("element-count").value
    let isValid = true
    const usedSymbols = new Set()
    const usedAtomicNumbers = new Set()

    for (let i = 0; i < count; i++) {
      const nombreInput = document.getElementById(`nombre-${i}`)
      const simboloInput = document.getElementById(`simbolo-${i}`)
      const numeroAtomicoInput = document.getElementById(`numeroAtomico-${i}`)
      const pesoAtomicoInput = document.getElementById(`pesoAtomico-${i}`)

      // Validación básica
      if (!nombreInput.validity.valid ||
          !simboloInput.validity.valid ||
          !numeroAtomicoInput.validity.valid ||
          !pesoAtomicoInput.validity.valid) {
        isValid = false
        continue
      }

      // Validación de símbolo único
      const simbolo = simboloInput.value.toUpperCase()
      if (usedSymbols.has(simbolo)) {
        document.getElementById(`simbolo-error-${i}`).textContent = "Este símbolo ya está en uso"
        isValid = false
      } else {
        usedSymbols.add(simbolo)
      }

      // Validación de número atómico único
      const numeroAtomico = numeroAtomicoInput.value
      if (usedAtomicNumbers.has(numeroAtomico)) {
        document.getElementById(`numeroAtomico-error-${i}`).textContent = "Este número atómico ya está en uso"
        isValid = false
      } else {
        usedAtomicNumbers.add(numeroAtomico)
      }
    }

    return isValid
  }

  // Collect data from all element forms
  function collectElementsData() {
    const count = document.getElementById("element-count").value
    const elements = []

    for (let i = 0; i < count; i++) {
      const element = {
        nombre: document.getElementById(`nombre-${i}`).value,
        simbolo: document.getElementById(`simbolo-${i}`).value,
        numeroAtomico: Number.parseInt(document.getElementById(`numeroAtomico-${i}`).value),
        pesoAtomico: Number.parseFloat(document.getElementById(`pesoAtomico-${i}`).value),
      }
      elements.push(element)
    }

    return elements
  }

  // API Functions
  async function saveElements(elements) {
    try {
      const response = await fetch(`${API_BASE_URL}/elementos`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(elements),
      })

      if (!response.ok) {
        const errorData = await response.json().catch(() => ({}))
        throw new Error(errorData.message || `Error del servidor: ${response.status}`)
      }

      return await response.json()
    } catch (error) {
      console.error("Error al guardar elementos:", error)
      throw new Error("No se pudieron guardar los elementos. Por favor, intente nuevamente.")
    }
  }

  async function fetchElements() {
    const response = await fetch(`${API_BASE_URL}/elementos`)

    if (!response.ok) {
      throw new Error(`HTTP error! status: ${response.status}`)
    }

    return await response.json()
  }

  async function fetchMaxAtomicNumber() {
    const response = await fetch(`${API_BASE_URL}/elementos/maximo-atomico`)

    if (!response.ok) {
      throw new Error(`HTTP error! status: ${response.status}`)
    }

    return await response.json()
  }

  async function fetchMaxAtomicWeight() {
    const response = await fetch(`${API_BASE_URL}/elementos/maximo-peso`)

    if (!response.ok) {
      throw new Error(`HTTP error! status: ${response.status}`)
    }

    return await response.json()
  }

  async function clearElements() {
    const response = await fetch(`${API_BASE_URL}/elementos`, {
      method: "DELETE",
    })

    if (!response.ok) {
      throw new Error(`HTTP error! status: ${response.status}`)
    }

    return await response.json()
  }

  // Display functions
  async function fetchAndDisplayElements() {
    try {
      showLoading()

      const elements = await fetchElements()
      const maxAtomicNumber = await fetchMaxAtomicNumber()
      const maxAtomicWeight = await fetchMaxAtomicWeight()

      displayElementsTable(elements)
      displayMaxElement(maxAtomicNumber, "max-atomic-number")
      displayMaxElement(maxAtomicWeight, "max-atomic-weight")

      resultsSection.classList.remove("hidden")
    } catch (error) {
      console.error("Error:", error)
      showNotification("Error al cargar los elementos", "error")
    } finally {
      hideLoading()
    }
  }

  function displayElementsTable(elements) {
    elementsTableBody.innerHTML = ""

    elements.forEach((element, index) => {
      const row = document.createElement("tr")
      row.innerHTML = `
                <td>${index + 1}</td>
                <td>${element.nombre}</td>
                <td>${element.simbolo}</td>
                <td>${element.numeroAtomico}</td>
                <td>${element.pesoAtomico.toFixed(3)}</td>
            `
      elementsTableBody.appendChild(row)
    })
  }

  function displayMaxElement(element, cardId) {
    const card = document.getElementById(cardId)
    const symbolDiv = card.querySelector(".element-symbol")
    const nameP = card.querySelector(".element-name")
    const valueP = card.querySelector(".element-value")
    const positionP = card.querySelector(".element-position")

    symbolDiv.textContent = element.simbolo
    nameP.textContent = `Nombre: ${element.nombre}`

    if (cardId === "max-atomic-number") {
      valueP.textContent = `Número Atómico: ${element.numeroAtomico}`
    } else {
      valueP.textContent = `Peso Atómico: ${element.pesoAtomico.toFixed(3)}`
    }

    positionP.textContent = `Posición: ${element.posicion + 1}`
  }

  // Utility functions
  function showLoading() {
    loadingIndicator.classList.remove("hidden")
  }

  function hideLoading() {
    loadingIndicator.classList.add("hidden")
  }

  function showNotification(message, type = "info") {
    notification.textContent = message
    notification.className = `notification ${type} show`

    setTimeout(() => {
      notification.classList.remove("show")
    }, 3000)
  }

  // Reset form
  resetBtn.addEventListener("click", () => {
    elementCountForm.reset()
    elementsForm.reset()
    elementsForm.classList.add("hidden")
    elementCountForm.classList.remove("hidden")
  })

  // Clear all data
  clearDataBtn.addEventListener("click", async () => {
    if (confirm("¿Está seguro que desea eliminar todos los elementos?")) {
      try {
        showLoading()
        await clearElements()
        resultsSection.classList.add("hidden")
        elementCountForm.classList.remove("hidden")
        elementCountForm.reset()
        showNotification("Todos los elementos han sido eliminados", "success")
      } catch (error) {
        console.error("Error:", error)
        showNotification("Error al eliminar los elementos", "error")
      } finally {
        hideLoading()
      }
    }
  })

  // Export to CSV
  exportBtn.addEventListener("click", async () => {
    try {
      const elements = await fetchElements()
      const csvContent = convertToCSV(elements)
      downloadCSV(csvContent, "elementos_quimicos.csv")
    } catch (error) {
      console.error("Error:", error)
      showNotification("Error al exportar los elementos", "error")
    }
  })

  function convertToCSV(elements) {
    const header = ["Posición", "Nombre", "Símbolo", "Número Atómico", "Peso Atómico"]
    const rows = elements.map((element, index) => [
      index + 1,
      element.nombre,
      element.simbolo,
      element.numeroAtomico,
      element.pesoAtomico.toFixed(3),
    ])

    return [header, ...rows].map((row) => row.join(",")).join("\n")
  }

  function downloadCSV(content, filename) {
    const blob = new Blob([content], { type: "text/csv;charset=utf-8;" })
    const link = document.createElement("a")

    const url = URL.createObjectURL(blob)
    link.setAttribute("href", url)
    link.setAttribute("download", filename)
    link.style.visibility = "hidden"

    document.body.appendChild(link)
    link.click()
    document.body.removeChild(link)
  }

  // Add example data for quick testing
  window.loadExampleData = () => {
    document.getElementById("element-count").value = 3
    elementCountForm.dispatchEvent(new Event("submit"))

    // Example data
    const exampleData = [
      { nombre: "Hidrógeno", simbolo: "H", numeroAtomico: 1, pesoAtomico: 1.008 },
      { nombre: "Helio", simbolo: "He", numeroAtomico: 2, pesoAtomico: 4.0026 },
      { nombre: "Litio", simbolo: "Li", numeroAtomico: 3, pesoAtomico: 6.94 },
    ]

    exampleData.forEach((data, index) => {
      document.getElementById(`nombre-${index}`).value = data.nombre
      document.getElementById(`simbolo-${index}`).value = data.simbolo
      document.getElementById(`numeroAtomico-${index}`).value = data.numeroAtomico
      document.getElementById(`pesoAtomico-${index}`).value = data.pesoAtomico
    })
  }
})
