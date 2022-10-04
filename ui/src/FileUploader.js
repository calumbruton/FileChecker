import React, { useState } from "react";
import axios from "axios";
import Button from '@mui/material/Button';

function FileUploader() {
  const [file, setFile] = useState(null);

  const UPLOAD_ENDPOINT =
    "http://localhost:8080/file/scan";

  const handleSubmit = async e => {
    e.preventDefault();
    //if await is removed, console log will be called before the uploadFile() is executed completely.
    //since the await is added, this will pause here then console log will be called
    let res = await uploadFile(file);
    console.log(res.data);
  };

  const uploadFile = async file => {
    const formData = new FormData();
    formData.append("file", file);

    return await axios.post(UPLOAD_ENDPOINT, formData, {
      headers: {
        "content-type": "multipart/form-data"
      }
    });
  };

  const handleOnChange = e => {
    console.log(e.target.files[0]);
    setFile(e.target.files[0]);
  };

  return (

      <div style={{
          textAlign: "center",
          maxWidth: "950px",
          margin: "0 auto",
          padding: "40px 25px",
          marginTop: "20%"
        }}>
        <Button variant="contained" 
                component="label" 
                color="primary"
                onChange={handleOnChange} >
          Upload
          <input hidden accept="image/*" multiple type="file" />
        </Button>
        <div class="space" style={{
            width: "20px",
            height: "auto",
            display: "inline-block"
            }}></div>
        <Button variant="contained" 
                component="label" 
                color="primary"
                onClick={handleSubmit} >
          Submit
        </Button>
      </div>
  );
}

export default FileUploader;
