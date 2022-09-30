import React, { useState } from "react";
import AppBar from '@mui/material/AppBar';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import IconButton from '@mui/material/IconButton';
import MenuIcon from '@mui/icons-material/Menu';
import Button from '@mui/material/Button';
import { createTheme, ThemeProvider } from '@mui/material/styles' 
import FileUploader from './FileUploader';


const theme = createTheme({
  palette: {
    primary: {
      main: "#FFD8B1"
    }
  },
  typography: {
    fontFamily: 'Roboto',
    fontWeightRegular: 500,
    fontWeightMedium: 600
  }
})


function App() {
  return (
    <ThemeProvider theme={theme}>
      <AppBar position="static">
        <Toolbar variant="dense">
          <IconButton edge="start" color="inherit" aria-label="menu" sx={{ mr: 2 }}>
            <MenuIcon />
          </IconButton>
          <Typography variant="h6" color="inherit" component="div">
            File Checker
          </Typography>
        </Toolbar>
      </AppBar>
      <FileUploader>
      </FileUploader>
    </ThemeProvider>
  );
}

export default App;


//Modify the UPLOAD_ENDPOINT with the API URL.
//The uploaded file can be retreived via $_FILES['avatar'] on the server-side(PHP).



