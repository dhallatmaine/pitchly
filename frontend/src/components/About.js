import { React, useState } from 'react'
import Search from "./Search";
import Button from "@mui/material/Button";
import { Typography } from '@mui/material';

function About(props) {
    const [isShown, setIsShown] = useState([]);

    const hide = event => {
        setIsShown(false);
      };

    return (
        <div style={{ width: '33%' }}>
            { isShown && 
                <Typography gutterBottom>
                    <p>
                        Pitchly is a new way to explore music. Artists join and leave bands often, when this happens they influence the sound of the band they play for.
                        Using this concept, we can recommend bands through related artists.
                    </p>
                    <br />
                    <Button
                        onClick={hide}
                        variant="contained"
                        size="large"
                        color="secondary"
                    >Start Exploring</Button>
                </Typography>
            }
            { !isShown && <Search /> }
        </div>
    )
}

export default About