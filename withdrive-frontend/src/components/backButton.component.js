import { useHistory } from "react-router-dom";
import { IconButton } from '@mui/material';
import ArrowBackIos from '@material-ui/icons/ArrowBackIos';

const Item = () => {
    let history = useHistory();
    return (
        <>
        <IconButton aria-label="delete" onClick={() => history.goBack()}>
            <ArrowBackIos/>
        </IconButton>
        </>
    );
};
export default Item;