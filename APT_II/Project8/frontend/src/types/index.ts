export interface MenuItem {
    id: number;
    name: string;
    description: string;
    price: number;
    isCombo: boolean;
}

export interface CalculationRequest {
    day: string;
    items: MenuItem[];
    email: string;
}

export interface CalculationResult {
    invoiceNumber: string;
    date: string;
    day: string;
    subtotal: number;
    discountAmount: number;
    discountPercentage: number;
    totalAfterDiscount: number;
    taxAmount: number;
    finalTotal: number;
    resultText: string;
    comboDiscount: boolean;
} 