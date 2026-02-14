import React from 'react'
import ReactDOM from 'react-dom/client'
import App from './App.jsx'
import 'bootstrap/dist/css/bootstrap.min.css'
import './styles/variables.css'
import './styles/theme.css'
import './styles/override.css'
import { ThemeProvider } from './context/ThemeContext.jsx'
// import './index.css' // Removing old index.css

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <ThemeProvider>
      <App />
    </ThemeProvider>
  </React.StrictMode>,
)
