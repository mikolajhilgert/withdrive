import useState from 'react';
import Select,{createFilter} from 'react-select';
import cities from '../data/nl.json'
import CustomOption from './selectFix.component';

const CitySelect = () =>{
    const [selected, setSelected] = useState("");
    return(
        <Select name="origin" value={origin} onChange={setSelected} options={cities.map(result=>result)}
        getOptionLabel={(option) => option.city}
        getOptionValue={(option) => option.city}
        filterOption={createFilter({ignoreAccents: false})}
        components={{ Option: CustomOption }}>
        </Select>
        
    )
}
export default CitySelect;