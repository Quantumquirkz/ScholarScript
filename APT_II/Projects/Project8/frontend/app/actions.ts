"use server"

interface InvoiceData {
  email: string
  invoiceNumber: string
  date: string
  day: string
  subtotal: number
  discountAmount: number
  discountPercentage: number
  totalAfterDiscount: number
  taxAmount: number
  finalTotal: number
  resultText: string
}

export async function sendInvoiceEmail(invoiceData: InvoiceData) {
  // Simular tiempo de envío estilo McDonald's
  await new Promise((resolve) => setTimeout(resolve, 2500))

  try {
    const emailContent = generateMcMenusEmailContent(invoiceData)

    console.log("Enviando factura McMENUS a:", invoiceData.email)
    console.log("Contenido del email:", emailContent)

    // Simular éxito del envío
    const success = Math.random() > 0.05 // 95% de éxito

    if (success) {
      return {
        success: true,
        message: "¡Factura McMENUS enviada exitosamente!",
      }
    } else {
      return {
        success: false,
        message: "Error al enviar la factura. ¡Inténtalo de nuevo!",
      }
    }
  } catch (error) {
    console.error("Error enviando email McMENUS:", error)
    return {
      success: false,
      message: "Error interno del servidor",
    }
  }
}

function generateMcMenusEmailContent(data: InvoiceData): string {
  return `
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>🍟 Factura McMENUS - ${data.invoiceNumber}</title>
    <style>
        body { 
            font-family: 'Arial', sans-serif; 
            line-height: 1.6; 
            color: #333; 
            margin: 0; 
            padding: 0;
            background: linear-gradient(135deg, #ff6b6b 0%, #ffd93d 100%);
        }
        .container {
            max-width: 600px;
            margin: 0 auto;
            background: white;
            border-radius: 15px;
            overflow: hidden;
            box-shadow: 0 10px 30px rgba(0,0,0,0.2);
        }
        .header { 
            background: linear-gradient(135deg, #dc2626 0%, #fbbf24 100%); 
            color: white; 
            padding: 30px 20px; 
            text-align: center; 
        }
        .header h1 {
            font-size: 2.5em;
            margin: 0;
            text-shadow: 2px 2px 4px rgba(0,0,0,0.3);
        }
        .content { 
            padding: 30px; 
        }
        .invoice-details { 
            background: linear-gradient(135deg, #fef3c7 0%, #fed7aa 100%); 
            padding: 20px; 
            border-radius: 10px; 
            margin: 20px 0; 
            border-left: 5px solid #dc2626;
        }
        .total { 
            background: linear-gradient(135deg, #dcfce7 0%, #bbf7d0 100%); 
            padding: 20px; 
            border-radius: 10px; 
            font-weight: bold; 
            text-align: center;
            border: 2px solid #16a34a;
        }
        .footer { 
            background: linear-gradient(135deg, #374151 0%, #1f2937 100%); 
            color: white;
            padding: 20px; 
            text-align: center; 
            font-size: 14px; 
        }
        table { 
            width: 100%; 
            border-collapse: collapse; 
            margin: 20px 0; 
            background: white;
            border-radius: 8px;
            overflow: hidden;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
        }
        th, td { 
            padding: 15px; 
            text-align: left; 
            border-bottom: 1px solid #e5e7eb; 
        }
        th { 
            background: linear-gradient(135deg, #dc2626 0%, #fbbf24 100%);
            color: white;
            font-weight: bold;
        }
        .combo-badge {
            background: #dc2626;
            color: white;
            padding: 4px 8px;
            border-radius: 12px;
            font-size: 12px;
            font-weight: bold;
        }
        .discount-highlight {
            background: #16a34a;
            color: white;
            padding: 2px 6px;
            border-radius: 4px;
            font-weight: bold;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="header">
            <h1>🍟 McMENUS 🍔</h1>
            <h2>¡Tu Factura Está Lista!</h2>
            <p style="margin: 0; opacity: 0.9;">I'm Lovin' It! ✨</p>
        </div>
        
        <div class="content">
            <div class="invoice-details">
                <h3 style="color: #dc2626; margin-top: 0;">📋 Detalles de tu Pedido</h3>
                <p><strong>🧾 Factura:</strong> ${data.invoiceNumber}</p>
                <p><strong>📅 Fecha:</strong> ${data.date}</p>
                <p><strong>👤 Cliente:</strong> ${data.email}</p>
                <p><strong>🗓️ Día especial:</strong> ${data.day}</p>
            </div>
            
            <table>
                <thead>
                    <tr>
                        <th>💰 Concepto</th>
                        <th>💵 Monto</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>Subtotal de tu pedido</td>
                        <td><strong>$${data.subtotal.toFixed(2)}</strong></td>
                    </tr>
                    <tr style="background: #fef3c7;">
                        <td>🎉 Descuento McMENUS <span class="discount-highlight">${(data.discountPercentage * 100).toFixed(0)}%</span></td>
                        <td style="color: #16a34a; font-weight: bold;">-$${data.discountAmount.toFixed(2)}</td>
                    </tr>
                    <tr>
                        <td>Subtotal con descuento</td>
                        <td><strong>$${data.totalAfterDiscount.toFixed(2)}</strong></td>
                    </tr>
                    <tr>
                        <td>Impuesto (7%)</td>
                        <td><strong>$${data.taxAmount.toFixed(2)}</strong></td>
                    </tr>
                </tbody>
            </table>
            
            <div class="total">
                <h2 style="margin: 0; color: #16a34a;">🎯 TOTAL A PAGAR: $${data.finalTotal.toFixed(2)}</h2>
            </div>
            
            ${
              data.discountPercentage > 0
                ? `<div style="background: linear-gradient(135deg, #dcfce7 0%, #bbf7d0 100%); padding: 20px; border-radius: 10px; margin: 20px 0; border-left: 5px solid #16a34a;">
                   <h4 style="color: #16a34a; margin-top: 0;">🎉 ¡Felicidades por tu Ahorro!</h4>
                   <p style="margin-bottom: 0;">Ahorraste <strong>$${(data.subtotal - data.finalTotal).toFixed(2)}</strong> con nuestros descuentos especiales McMENUS. ¡Sigue visitándonos para más ofertas increíbles!</p>
                 </div>`
                : `<div style="background: linear-gradient(135deg, #fef3c7 0%, #fed7aa 100%); padding: 20px; border-radius: 10px; margin: 20px 0; border-left: 5px solid #f59e0b;">
                   <h4 style="color: #f59e0b; margin-top: 0;">💡 ¡No te pierdas nuestros descuentos!</h4>
                   <p style="margin-bottom: 0;">Visítanos de lunes a viernes y ordena nuestros combos especiales para obtener hasta <strong>50% de descuento</strong>. ¡Te esperamos!</p>
                 </div>`
            }
            
            <div style="text-align: center; margin: 30px 0;">
                <p style="font-size: 18px; color: #dc2626; font-weight: bold;">🍟 ¡Gracias por elegir McMENUS! 🍔</p>
                <p style="color: #6b7280;">Esperamos verte pronto para más deliciosas ofertas</p>
            </div>
        </div>
        
        <div class="footer">
            <p><strong>🍟 McMENUS - McDonald's</strong></p>
            <p>Sistema de Facturación Electrónica</p>
            <p style="margin: 0; opacity: 0.8;">Esta factura ha sido generada automáticamente</p>
        </div>
    </div>
</body>
</html>
  `
}
