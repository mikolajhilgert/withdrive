import {useState} from 'react';
import Select,{createFilter} from 'react-select';
import cities from '../data/nl.json'
import CustomOption from './selectFix.component';

const CitySelect = (props) =>{
    const [city, setCity] = useState("");

    const handleSelection = (data) => {
        if(data !== null){
            setCity(cities.find(o => o.city === data.city));
            props.setCity(data.city);
        }else{
            setCity("");
            props.setCity("");
        }
    }

    var a = false;
    if(props.clear===true){
        a = true;
    }

    return(
        <Select name="origin" value={city} onChange={handleSelection} 
        options={cities.map(result=>result)}
        getOptionLabel={(option) => option.city}
        getOptionValue={(option) => option.city}
        filterOption={createFilter({ignoreAccents: false})}
        components={{ Option: CustomOption }}
        isClearable={a}
        placeholder={props.text}/>
    )
}
export default CitySelect;