import { type NextRequest, NextResponse } from "next/server"

export async function POST(request: NextRequest) {
  try {
    const { initialVelocity, launchAngle } = await request.json()

    // Aquí harías la llamada a tu backend Java
    // Por ejemplo, usando fetch para llamar a tu API Java:

    // const javaBackendUrl = process.env.JAVA_BACKEND_URL || 'http://localhost:8080'
    // const response = await fetch(`${javaBackendUrl}/api/calculate-projectile`, {
    //   method: 'POST',
    //   headers: {
    //     'Content-Type': 'application/json',
    //   },
    //   body: JSON.stringify({
    //     initialVelocity,
    //     launchAngle
    //   })
    // })

    // if (!response.ok) {
    //   throw new Error('Error calling Java backend')
    // }

    // const results = await response.json()
    // return NextResponse.json(results)

    // Simulación para demostración
    const g = 9.81
    const angleRad = (launchAngle * Math.PI) / 180

    const totalFlightTime = (2 * initialVelocity * Math.sin(angleRad)) / g
    const timeToMaxHeight = (initialVelocity * Math.sin(angleRad)) / g
    const maxHeight = (initialVelocity * initialVelocity * Math.sin(angleRad) * Math.sin(angleRad)) / (2 * g)
    const maxDistance = (initialVelocity * initialVelocity * Math.sin(2 * angleRad)) / g

    const trajectory = []
    for (let t = 0; t <= Math.ceil(totalFlightTime); t++) {
      const height = Math.max(0, initialVelocity * Math.sin(angleRad) * t - 0.5 * g * t * t)
      const horizontalVelocity = initialVelocity * Math.cos(angleRad)
      const verticalVelocity = initialVelocity * Math.sin(angleRad) - g * t
      const horizontalPosition = initialVelocity * Math.cos(angleRad) * t

      trajectory.push({
        time: t,
        height: Number.parseFloat(height.toFixed(2)),
        horizontalVelocity: Number.parseFloat(horizontalVelocity.toFixed(2)),
        verticalVelocity: Number.parseFloat(verticalVelocity.toFixed(2)),
        horizontalPosition: Number.parseFloat(horizontalPosition.toFixed(2)),
      })

      if (height <= 0 && t > 0) break
    }

    return NextResponse.json({
      totalFlightTime: Number.parseFloat(totalFlightTime.toFixed(2)),
      timeToMaxHeight: Number.parseFloat(timeToMaxHeight.toFixed(2)),
      maxHeight: Number.parseFloat(maxHeight.toFixed(2)),
      maxDistance: Number.parseFloat(maxDistance.toFixed(2)),
      trajectory,
    })
  } catch (error) {
    return NextResponse.json({ error: "Error calculating projectile motion" }, { status: 500 })
  }
}
