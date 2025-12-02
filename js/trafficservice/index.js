const soap = require('soap');
const fs = require('fs');
const uuid = require('uuid');
const express = require('express');

const wsdlFile = fs.readFileSync("TrafficService.wsdl", "utf-8");


const TrafficSensorActivationImpl = function(params){
  return {
    SensorID: uuid.v4(),
    ActivationStatus: "activated",
    ActivationDate: "2.12.2025"
  }
}

const TrafficFlowImpl = function(params){
  return {
    Response: "OK"
  }
}

const serviceObject = {
  TrafficService: {
    TrafficServiceSOAP: {
      TrafficSensorActivation: TrafficSensorActivationImpl,
      TrafficFlow: TrafficFlowImpl
    }
  }
}


const PORT = 9090;
const path = "/TrafficService";

const app = express();

app.listen(PORT, ()=>{
  soap.listen(app, path, serviceObject, wsdlFile, ()=>{
    console.log("Storitev je na voljo na http://localhost:9090" + path + "?wsdl");
  });
});
