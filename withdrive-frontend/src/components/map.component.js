import React, { useEffect, useState, Component } from "react";
import { MapContainer, TileLayer, Marker, Polyline, Popup } from "react-leaflet";
import L from "leaflet";
import markerstart from "../images/marker_start.png";
import markerend from "../images/marker_end.png";
import "leaflet/dist/leaflet.css";
import osm from "../data/maptiler";
import places from "../data/nl.json"
import ChangeView from "./changeView.component"

const markerEnd = new L.Icon({
    iconUrl: markerend,
    iconSize: [40, 40],
    iconAnchor: [20, 38], 
    popupAnchor: [0, -0], 
    });
    
    const markerStart = new L.Icon({
    iconUrl: markerstart,
    iconSize: [40, 40],
    
    iconAnchor: [17, 20],
    popupAnchor: [0, -20],
});


const Map = (props) =>{
    let startCoordinate = [0,0];
    let endCoordinate = [0,0];

    places.map(result=>(result)).forEach(function(element) {
        if (element.city === props.trip.origin) {
            startCoordinate = [element.lat,element.lng];
        }
        if (element.city === props.trip.destination) {
            endCoordinate = [element.lat,element.lng];
        }
    });

    const center = {lat: (parseFloat(startCoordinate[0])+parseFloat(endCoordinate[0]))/2, lng: (parseFloat(startCoordinate[1])+parseFloat(endCoordinate[1]))/2};

    return(
    <MapContainer className="form-control" center={{lat: 52.35498373438076, lng: 5.247254814000781}} zoom={7} maxZoom={11} minZoom={7}>
        <ChangeView center={center} zoom={8} /> 
        <TileLayer url={osm.maptiler.url} attribution={osm.maptiler.attribution} />
        <Marker position={startCoordinate} icon={markerStart}>
            <Popup>{props.trip.origin}</Popup>
        </Marker>
        <Marker position={endCoordinate} icon={markerEnd}>
            <Popup>{props.trip.destination}</Popup>
        </Marker>
        <Polyline positions={[startCoordinate,endCoordinate,]}></Polyline>
    </MapContainer>
    )

}
export default Map;