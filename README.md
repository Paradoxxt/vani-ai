<h1 align="center">🗣️ Vani AI (वाणी)</h1>

<p align="center">
  <b>A cost-efficient, multilingual voice assistant for budget Bluetooth speakers</b><br/>
  <i>Making smart voice assistance accessible to every Indian household</i>
</p>

<p align="center">
  <a href="https://openjdk.org/"><img src="https://img.shields.io/badge/Java-17+-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java"/></a>
  <a href="https://maven.apache.org/"><img src="https://img.shields.io/badge/Maven-Build-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white" alt="Maven"/></a>
  <a href="https://www.sarvam.ai/"><img src="https://img.shields.io/badge/Sarvam_AI-Indic_Models-blueviolet?style=for-the-badge" alt="Sarvam AI"/></a>
  <a href="https://opensource.org/licenses/MIT"><img src="https://img.shields.io/badge/License-MIT-yellow.svg?style=for-the-badge" alt="License: MIT"/></a>
</p>

---

## 💡 The Problem

Smart voice assistants like Amazon Alexa and Google Home rely on proprietary, expensive hardware costing upwards of ₹5,000. Meanwhile, millions of Indian households already own capable, affordable Bluetooth speakers from brands like **boAt, Zebronics, and Portronics** (costing ₹800–₹1,500) that serve strictly as passive audio output sinks.

**Why buy a ₹5,000 proprietary hub when you already own a functional speaker?**

---

## 🎯 Vision

Vani AI decouples voice intelligence from dedicated speaker hardware:

- **Hardware Agnostic:** Directs synthesized audio to any paired Bluetooth speaker over standard A2DP profiles.
- **Indic Language Native:** Built specifically for regional phonetics and cross-lingual translation across Hindi, Bengali, and other major Indian languages.
- **Edge-Focused Execution:** Designed to run on low-power devices (Android devices via Termux or Raspberry Pi Zero 2W).
- **Hybrid Connectivity:** Uses offline engines for local commands and low-latency Indic cloud models for advanced conversational queries.

---

## 🏗️ System Architecture

<pre>
[ User Voice / Query Input ]
            │
            ▼
┌────────────────────────────────────────────────────────┐
│ Vani AI Host Service (Java Engine)                     │
│                                                        │
│  [ Audio Capture & Stream Processor ]                  │
│            │                                           │
│            ▼                                           │
│  [ Wake Word Detection ] ── ("Hey Vani")               │
│            │                                           │
│            ▼                                           │
│  [ Indic NLP & Speech Layer ]                          │
│      ├── Local Fallback (Vosk / Offline Grammar)       │
│      └── Sarvam AI API (Indic STT, Translation & TTS)  │
│            │                                           │
│            ▼                                           │
│  [ Audio Dispatch Engine ]                             │
└────────────────────────────────────────────────────────┘
            │
            ▼ (Bluetooth A2DP Stream)
[ Budget Bluetooth Speaker (boAt / Zebronics / Portronics) ]
</pre>

---

## 🧠 Core Capabilities

| Capability | Status | Description |
|---|---|---|
| **🌐 Indic Translation & NLP** | 🟢 Implemented | REST integration with Sarvam AI for low-latency Hindi and Indic language pipelines |
| **🔊 Bluetooth A2DP Sink** | 🟡 In Progress | Audio routing to external budget Bluetooth audio sinks |
| **🎙️ Wake Word Detection** | 🟡 In Progress | Lightweight on-device wake-word detection engine ("Hey Vani") |
| **📡 Offline Command Mode** | ⚪ Planned | Offline speech intent parsing for core commands (volume, media, system status) |
| **🏠 Local IoT Hooks** | ⚪ Planned | Lightweight MQTT/HTTP relays for budget ESP8266 smart home controls |

---

## 🛠️ Tech Stack

- **Core Engine:** Java 17+, Maven
- **Indic NLP & Translation:** Sarvam AI REST API
- **Networking & Serialization:** Java HTTP Client, Jackson JSON
- **Audio & Bluetooth Layer:** BlueZ / PulseAudio / Java Sound API
- **Target Edge Hardware:** Raspberry Pi (Zero 2W / 4B) / Linux / Android

---

## 🚀 Getting Started

### Prerequisites

- **Java Development Kit (JDK 17+)** installed and added to `PATH`
- **Apache Maven 3.8+**
- A valid API key from [Sarvam AI](https://www.sarvam.ai/)

### Setup Instructions

1. **Clone the repository:**
   ```bash
   git clone [https://github.com/Paradoxxt/vani-ai.git](https://github.com/Paradoxxt/vani-ai.git)
   cd vani-ai
