import { React, useState } from 'react'
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import PlayCircle from '@mui/icons-material/PlayCircle';
import List from '@mui/material/List';
import ListItem from '@mui/material/ListItem';
import ListItemText from '@mui/material/ListItemText';
import { Divider } from '@mui/material';

function Search(props) {
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
            <Button
                onClick={fetchData}
                variant="contained">
                Fetch Bands&nbsp;
                <PlayCircle></PlayCircle>
            </Button>
            <List component="nav" aria-label="mailbox folders">
                {bands.map((band) => (
                <div>
                    <ListItem button>
                        <ListItemText primary={band.name} secondary={band.level} />
                    </ListItem>
                    <Divider />
                </div>
                ))}
            </List>
        </div>
    )
}

export default Search