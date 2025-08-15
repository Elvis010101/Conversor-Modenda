# 💱 Conversor de Monedas en Java con ExchangeRate-API

Este proyecto es una aplicación de consola en **Java** que consume la API pública de **[ExchangeRate-API](https://www.exchangerate-api.com/)** para convertir **dólares estadounidenses (USD)** a varias monedas de Latinoamérica.

## 🚀 Características
- Consulta las tasas de cambio en tiempo real desde la API.
- Conversión de USD a:
  - 🇦🇷 Pesos Argentinos (ARS)
  - 🇧🇴 Bolivianos (BOB)
  - 🇧🇷 Reales Brasileños (BRL)
  - 🇨🇱 Pesos Chilenos (CLP)
  - 🇨🇴 Pesos Colombianos (COP)
- Menú interactivo en consola.
- Manejo de errores HTTP y respuestas inválidas.

---

## 📂 Estructura del Proyecto
│── src/
│ └── Main.java # Código principal de la aplicación
│── pom.xml # Configuración Maven (dependencia Gson)
│── README.md # Documentación del proyecto

---

## 🔧 Requisitos Previos
- **Java 11** o superior (usa `HttpClient` moderno).
- **Maven** (para gestión de dependencias).
- Una **API Key** gratuita de [ExchangeRate-API](https://www.exchangerate-api.com/).

---

## 📦 Instalación y Uso
1. **Clona este repositorio**:
   ```bash
   git clone https://github.com/Elvis010101/conversor-monedas-java.git
   cd conversor-monedas-java

mvn compile
mvn exec:java -Dexec.mainClass="Main"
