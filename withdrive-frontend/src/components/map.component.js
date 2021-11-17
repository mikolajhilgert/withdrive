import React, { useState, Component } from "react";
import { MapContainer, TileLayer, Marker, Polyline, Popup } from "react-leaflet";
import L from "leaflet";
import markerstart from "../images/marker_start.png";
import markerend from "../images/marker_end.png";
import "leaflet/dist/leaflet.css";
import osm from "../data/maptiler";
import places from "../data/nl.json"

let startCoordinate = [0,0];
let endCoordinate = [0,0];

places.map(result=>(result)).forEach(function(element) {
    if (element.city === "Eindhoven") {
        startCoordinate=[element.lat,element.lng];
    }
    if (element.city === "Utrecht") {
        endCoordinate = [element.lat,element.lng];
    }
});




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

const Map = () =>{


// const [center, setCenter] = useState({ lat: startCoordinate[0]-(startCoordinate[0]-endCoordinate[0]), lng: startCoordinate[1]-(startCoordinate[1]-endCoordinate[1]) });
const [center, setCenter] = useState({ lat: ((parseFloat(startCoordinate[0])+parseFloat(endCoordinate[0]))/2),lng: ((parseFloat(startCoordinate[1])+parseFloat(endCoordinate[1]))/2) });
const ZOOM_LEVEL = 9;

const polyline = [
    startCoordinate,
    endCoordinate,
];
    return(
    <MapContainer className="form-control" center={center} zoom={ZOOM_LEVEL}>
        <TileLayer url={osm.maptiler.url} attribution={osm.maptiler.attribution} />
        <Marker position={startCoordinate} icon={markerStart}>
            <Popup>Trip end</Popup>
        </Marker>
        <Marker position={endCoordinate} icon={markerEnd}>
            <Popup>Trip start</Popup>
        </Marker>
        <Polyline positions={polyline}></Polyline>
        {/* pathOptions={{color:"lime"}} */}
    </MapContainer>
    )

}
export default Map;