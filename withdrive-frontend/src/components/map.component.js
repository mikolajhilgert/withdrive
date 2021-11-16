import React, { useState } from "react";
import { MapContainer, TileLayer, Marker, Polyline, Popup } from "react-leaflet";
import L from "leaflet";
import markerstart from "../images/marker_start.png";
import markerend from "../images/marker_end.png";
import "leaflet/dist/leaflet.css";
import osm from "../data/maptiler";

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
const startCoordinate = [51.4408, 5.4778];
const endCoordinate = [52.0908, 5.1222];

const [center, setCenter] = useState({ lat: startCoordinate[0]-(startCoordinate[0]-endCoordinate[0]), lng: startCoordinate[1]-(startCoordinate[1]-endCoordinate[1]) });
const ZOOM_LEVEL = 8;

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
    </MapContainer>
    )

}
export default Map;