import { React, useState, useEffect } from 'react'
import TextField from "@mui/material/TextField";


function List(props) {
    //create a new array by filtering the original array
    const [bands, setBands] = useState([]);
    const [inputText, setInputText] = useState("");

    const fetchData = () => {
        fetch('http://localhost:8080/?band=' + inputText)
        .then(response => response.json())
          .then(data => {
            console.log(data);
            setBands(data);
        });
    }

    let inputHandler = (e) => {
        //convert input text to lower case
        var lowerCase = e.target.value.toLowerCase();
        setInputText(lowerCase);
    };

    return (
        <div>
            <TextField
                id="outlined-basic"
                onChange={inputHandler}
                variant="outlined"
                fullWidth
                label="Search"
            />
            <button onClick={fetchData}>Fetch Bands</button>
            <ul>
                {bands.map((band) => (
                    <li key={band}>{band}</li>
                ))}
            </ul>
        </div>
    )
}

export default List