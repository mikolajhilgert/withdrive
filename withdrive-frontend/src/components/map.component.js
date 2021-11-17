import React, { useEffect, useState, Component } from "react";
import { MapContainer, TileLayer, Marker, Polyline, Popup } from "react-leaflet";
import L from "leaflet";
import markerstart from "../images/marker_start.png";
import markerend from "../images/marker_end.png";
import "leaflet/dist/leaflet.css";
import osm from "../data/maptiler";
import places from "../data/nl.json"

let center;

const markerEnd = new L.Icon({
    iconUrl: markerend,
    iconSize: [40, 40],
    iconAnchor: [20, 38], //[left/right, top/bottom]
    popupAnchor: [0, -0], //[left/right, top/bottom]
    });
    
    const markerStart = new L.Icon({
    iconUrl: markerstart,
    iconSize: [40, 40],
    
    iconAnchor: [17, 20], //[left/right, top/bottom]
    popupAnchor: [0, -20], //[left/right, top/bottom]
});

const Map = (props) =>{
    let startCoordinate = [0,0];
    let endCoordinate = [0,0];

    const ZOOM_LEVEL = 7;

    places.map(result=>(result)).forEach(function(element) {
        if (element.city === props.trip.origin) {
            startCoordinate = [element.lat,element.lng];
        }
        if (element.city === props.trip.destination) {
            endCoordinate = [element.lat,element.lng];
        }
    });




    // 
    //const [center, setCenter] = useState({ lat: ((parseFloat(startCoordinate[0])+parseFloat(endCoordinate[0]))/2),lng: ((parseFloat(startCoordinate[1])+parseFloat(endCoordinate[1]))/2) });

    // useEffect(() => {
    //     center= { lat: ((parseFloat(startCoordinate[0])+parseFloat(endCoordinate[0]))/2),lng: ((parseFloat(startCoordinate[1])+parseFloat(endCoordinate[1]))/2) };
    //   }, []);

// const [center, setCenter] = useState({ lat: startCoordinate[0]-(startCoordinate[0]-endCoordinate[0]), lng: startCoordinate[1]-(startCoordinate[1]-endCoordinate[1]) });


console.log(center);
    return(
    <MapContainer className="form-control" center={{lat: 52.04309089702452, lng: 4.924968925921649}} zoom={ZOOM_LEVEL}>
        <TileLayer url={osm.maptiler.url} attribution={osm.maptiler.attribution} />
        <Marker position={startCoordinate} icon={markerStart}>
            <Popup>{props.trip.origin}</Popup>
        </Marker>
        <Marker position={endCoordinate} icon={markerEnd}>
            <Popup>{props.trip.destination}</Popup>
        </Marker>
        <Polyline positions={[    startCoordinate,
    endCoordinate,]}></Polyline>
        {/* pathOptions={{color:"lime"}} */}
    </MapContainer>
    )

}
export default Map;