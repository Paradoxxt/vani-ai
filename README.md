<h1 align="center">🗣️ Vani AI</h1>

<p align="center">
  <b>A cost-efficient, multilingual voice assistant for budget Bluetooth speakers</b><br/>
  <i>Making smart voice assistance accessible to every Indian household</i>
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Status-Planning%20Stage-yellow?style=for-the-badge"/>
  <img src="https://img.shields.io/badge/Languages-All%20Major%20Indian-orange?style=for-the-badge"/>
  <img src="https://img.shields.io/badge/Target-Budget%20BT%20Speakers-blue?style=for-the-badge"/>
</p>

---

## 💡 The Problem

Smart voice assistants like Amazon Alexa and Google Home are powerful — but they require expensive proprietary hardware. Millions of Indians already own budget Bluetooth speakers from brands like **boAt, Zebronics, Portronics**, and more. These devices have no "smart" capability despite being perfectly capable audio output devices.

**Why buy a ₹5000 smart speaker when you already have a ₹800 one?**

---

## 🎯 Vision

Vani AI aims to be a **cost-efficient, locally-aware voice assistant** that:

- Works with **any Bluetooth speaker** — no new hardware needed
- Supports **all major Indian languages** (Hindi, Bengali, Tamil, Telugu, Marathi, Kannada, Gujarati, and more)
- Runs on a **low-cost device** (smartphone or Raspberry Pi)
- Provides **region-aware responses** tailored for Indian users
- Operates with **minimal internet dependency** for core features

---

## 🧠 Planned Features

| Feature | Description |
|---|---|
| 🎙️ Wake Word Detection | Custom wake word (e.g. "Hey Vani") |
| 🌐 Multilingual NLP | Understands and responds in 10+ Indian languages |
| 🔊 BT Speaker Integration | Seamless pairing with budget Bluetooth devices |
| 📡 Offline Mode | Core commands work without internet |
| 🏠 Smart Home Hooks | Control lights, fans via basic IoT integration |
| 🎵 Media Control | Play music, control volume, skip tracks |
| 📰 Local News & Weather | Region-specific updates in your language |

---

## 🏗️ Architecture (Planned)

<pre>
User Voice Input
      ↓
Wake Word Detection
      ↓
Speech-to-Text (Multilingual)
      ↓
Intent Recognition / NLP Engine
      ↓
Response Generator (Localized)
      ↓
Text-to-Speech → Bluetooth Speaker Output
</pre>

---

## 🛠️ Tech Stack (Under Evaluation)

- **Speech Recognition:** Whisper (OpenAI) / Vosk (offline)
- **NLP / Intent:** Rasa / custom model
- **Backend:** Java (Spring Boot) / Python
- **TTS:** Google TTS / Coqui TTS (offline)
- **Hardware Target:** Android phone / Raspberry Pi Zero 2W
- **BT Communication:** Android Bluetooth API / BlueZ (Linux)

---

## 🗺️ Roadmap

- [ ] Finalize tech stack
- [ ] Build basic wake word detection prototype
- [ ] Implement Hindi + Bengali STT pipeline
- [ ] Add remaining Indian language support
- [ ] Integrate with Bluetooth audio output
- [ ] Offline mode for core commands
- [ ] Public beta release

---

## 🤝 Contributing

This project is in early planning. Ideas, suggestions, and contributions are very welcome!

1. Fork the repo
2. Create a feature branch (`git checkout -b feature/your-idea`)
3. Commit your changes
4. Open a Pull Request

---

## 👨‍💻 Author

**Subhojit Samanta** — [@Paradoxxt](https://github.com/Paradoxxt)

> *"Voice assistance shouldn't be a luxury — it should be for everyone."*

---

<p align="center">⭐ Star this repo if you believe in accessible AI for India!</p>
