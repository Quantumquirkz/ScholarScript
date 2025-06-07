const calculateTotal = async () => {
    setError("");
    setEmailSent(false);
    setIsCalculating(true);

    try {
        if (!validateInput(selectedDay, selectedItems, email)) {
            setIsCalculating(false);
            return;
        }

        const request: CalculationRequest = {
            day: selectedDay,
            items: selectedItems,
            email: email
        };

        const result = await calculateOrder(request);
        setResult(result);
    } catch (error) {
        setError("Error al calcular el pedido. Por favor, intenta de nuevo.");
    } finally {
        setIsCalculating(false);
    }
}; 