import { React, useState } from "react";
import About from "./components/About";
import "./App.css";
import logo from './logo.png';
import { createTheme, ThemeProvider } from '@mui/material';

const theme = createTheme({
  typography: {
    fontFamily: [
      'Roboto Mono',
    ].join(','),
  },});

function App() {

  return (
    <ThemeProvider theme={theme}>
      <div className="main">
        <img src={logo} alt="Pitchly" width="13%" />
        <About />
      </div>
    </ThemeProvider>
  );
}

export default App;