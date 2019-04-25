var DeviceInformationLoader = function(require, exports, module) {
  var exec = require("cordova/exec");

  function DeviceInformation() {}

  DeviceInformation.prototype.getDeviceInfo = function(success, failure) {
    exec(success, failure, "AndroidDeviceInformation", "getDeviceInfo", []);
  };

  var deviceInformation = new DeviceInformation();
  module.exports = deviceInformation;
};

DeviceInformationLoader(require, exports, module);

cordova.define("cordova/plugin/DeviceInformation", DeviceInformationLoader);
