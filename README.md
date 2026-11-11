# Calculadora Matemática con Interfaz Gráfica

Aplicación de escritorio en **Java (Swing)** que combina una calculadora y un conversor de temperatura en una misma ventana, organizada en dos pestañas.

## Funcionalidades

### Pestaña "Calculadora"
| Operación | Descripción |
|-----------|-------------|
| ➕ Sumar | `n1 + n2` |
| ➖ Restar | `n1 - n2` |
| ✖️ Multiplicar | `n1 × n2` |
| ➗ Dividir | `n1 ÷ n2`, con validación de división por cero |
| √ Raíz cuadrada | Raíz del **Número 1** (no permite negativos) |
| xʸ Exponente | `n1` elevado a `n2` |

### Pestaña "Conversor de temperatura"
- **°F → °C:** `°C = (°F − 32) × 5/9`
- **°C → °F:** `°F = (°C × 9/5) + 32`

## Validaciones

- **División entre cero:** se comprueba con un `if` y se muestra un mensaje de error claro (en rojo y en un cuadro de diálogo).
- **Raíz de un número negativo:** muestra un mensaje de error.
- **Entrada no numérica:** si el usuario escribe texto en lugar de un número, se informa del error sin cerrar el programa.
- Se acepta la coma como separador decimal (`3,5` equivale a `3.5`).

## Estructura del proyecto

```
cmatematica/
├── CMatematica.java      # Lógica: operaciones y conversiones
├── CalculadoraGUI.java   # Interfaz gráfica (contiene el main)
└── README.md
```

- **`CMatematica`**: guarda los dos números (`darNumeros`), realiza las operaciones y lanza `ArithmeticException` cuando una operación no es válida. Las conversiones de temperatura son métodos `static`.
- **`CalculadoraGUI`**: construye la ventana con `JFrame` y `JTabbedPane`, lee los datos de los campos de texto y muestra los resultados.

## Requisitos

- JDK 8 o superior (usa expresiones lambda).
- Un IDE como NetBeans, o la línea de comandos.

## Cómo ejecutarlo

### Con NetBeans
1. Crea un proyecto Java y un paquete llamado `cmatematica`.
2. Copia dentro `CMatematica.java` y `CalculadoraGUI.java`.
3. Ejecuta `CalculadoraGUI.java` (clic derecho → *Run File*).

### Desde la terminal
```bash
javac -d out cmatematica/*.java
java -cp out cmatematica.CalculadoraGUI
```

## Ejemplos de uso

| Entrada | Operación | Resultado |
|---------|-----------|-----------|
| 8 y 2 | Dividir | 4.0 |
| 8 y 0 | Dividir | Error: no se puede dividir entre cero. |
| 49 | Raíz | 7.0 |
| 2 y 10 | Exponente | 1024.0 |
| 212 | °F → °C | 100.00 °C |
| 0 | °C → °F | 32.00 °F |

## Autor

Matthews Batista
