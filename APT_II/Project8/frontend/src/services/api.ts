import { CalculationRequest, CalculationResult } from '../types';

const API_URL = '/api';

export const calculateOrder = async (request: CalculationRequest): Promise<CalculationResult> => {
    const response = await fetch(`${API_URL}/calculate`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(request),
    });

    if (!response.ok) {
        throw new Error('Error al calcular el pedido');
    }

    return response.json();
};

export const sendInvoice = async (result: CalculationResult): Promise<void> => {
    const response = await fetch(`${API_URL}/send-invoice`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(result),
    });

    if (!response.ok) {
        throw new Error('Error al enviar la factura');
    }
}; 