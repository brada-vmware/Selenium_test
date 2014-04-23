var webdriver = require('selenium-webdriver');

var driver = new webdriver.Builder().
   usingServer('http://vcac-jenkins-sandbox1.eng.vmware.com:4444/wd/hub').
   withCapabilities(webdriver.Capabilities.firefox()).
   build();

driver.get('http://www.google.com/');

driver.getTitle().then(function(title) {
  driver.getPageSource().then(function(html) {
    console.log(html);
    return true;
  });
});


//SAVE A SNAPSHOT IN A PNG IMAGE
var fs = require('fs');

webdriver.WebDriver.prototype.saveScreenshot = function(filename) {
  return driver.takeScreenshot().then(function(data) {
    fs.writeFile(filename, data.replace(/^data:image\/png;base64,/,''), 'base64', function(err) {
      if(err) throw err;
    });
  })
};

driver.saveScreenshot('./screenshot.png');

driver.quit();
