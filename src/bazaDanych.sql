CREATE TABLE IF NOT EXISTS `country` (
  `countryid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`countryid`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=12 ;

CREATE TABLE IF NOT EXISTS `temp` (
  `tekst` varchar(20) NOT NULL,
  `numer_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`numer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

CREATE TABLE IF NOT EXISTS `service` (
  `serviceid` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(100) NOT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`serviceid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

CREATE TABLE IF NOT EXISTS `payment` (
  `paymentid` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(50) NOT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`paymentid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

CREATE TABLE IF NOT EXISTS `status` (
  `statusid` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(30) NOT NULL,
  PRIMARY KEY (`statusid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

CREATE TABLE IF NOT EXISTS `data` (
  `dataid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `countryid` int(11) NOT NULL,
  `details` varchar(200) NOT NULL,
  `zipcode` varchar(11) NOT NULL,
  `city` varchar(50) NOT NULL,
  `tel` varchar(16) NOT NULL,
  `mail` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`dataid`) ,
  FOREIGN KEY (`countryid`)
  REFERENCES `country`(countryid)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=21 ;


CREATE TABLE IF NOT EXISTS `user` (
  `userid` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `login` varchar(30) NOT NULL,
  `password` varchar(50) NOT NULL,
  `dataid` int(11) NOT NULL,
  `nip` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`userid`),
  UNIQUE KEY `userid` (`userid`) ,
  FOREIGN Key (`dataid`)
  REFERENCES data(dataid)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;


CREATE TABLE IF NOT EXISTS `person` (
  `userid` int(11) unsigned NOT NULL,
  `forename` varchar(30) NOT NULL,
  `surname` varchar(30) NOT NULL,
  FOREIGN KEY (userid)
  REFERENCES USER(userid)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE IF NOT EXISTS `place` (
  `userid` int(11) unsigned NOT NULL,
  `dataid` int(11) NOT NULL,
  `title` varchar(100) DEFAULT NULL  ,
  FOREIGN KEY (`userid`)
  references   `user`(`userid`),
  FOREIGN KEY (dataid)
  references data(dataid)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `corporation` (
  `userid` int(11) unsigned NOT NULL,
  `name` varchar(100) NOT NULL,
  `regon` varchar(20) NOT NULL,
FOREIGN KEY (userid)
  REFERENCES USER(userid)
  
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `weight` (
  `weightid` int(11) NOT NULL AUTO_INCREMENT,
  `intervalf` decimal(10,4) NOT NULL,
  `intervalt` decimal(10,4) NOT NULL,
  `price` decimal(10,2) NOT NULL,
  PRIMARY KEY (`weightid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

CREATE TABLE IF NOT EXISTS `base` (
  `baseid` int(11) NOT NULL AUTO_INCREMENT,
  `dataid` int(11) NOT NULL,
  `headid` int(11) DEFAULT NULL,
  PRIMARY KEY (`baseid`),
  FOREIGN KEY (dataid)
  REFERENCES data(dataid)
  
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

CREATE TABLE IF NOT EXISTS `address_book` (
  `userid` int(11) unsigned NOT NULL,
  `dataid` int(11) NOT NULL,
  `title` varchar(100) NOT NULL,
  FOREIGN KEY (`userid`)
  references   `user`(`userid`),
  FOREIGN KEY (dataid)
  references data(dataid)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `employee` (
  `employeeid` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(30) NOT NULL,
  `password` varchar(50) NOT NULL,
  `forename` varchar(30) NOT NULL,
  `surname` varchar(30) NOT NULL,
  `dataid` int(11) NOT NULL,
  `empdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `earnings` decimal(10,2) NOT NULL,
  `baseid` int(11) DEFAULT NULL,
  `worktime` varchar(100) DEFAULT NULL,
  `available` tinyint(1) NOT NULL,
  `nip` decimal(10,0) NOT NULL,
  `account` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`employeeid`) ,
 FOREIGN KEY (dataid)
  REFERENCES data(dataid),
   FOREIGN KEY (baseid)
  REFERENCES base(baseid)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

CREATE TABLE IF NOT EXISTS `operator` (
  `operatorid` int(11) NOT NULL AUTO_INCREMENT,
  `employeeid` int(11) NOT NULL,
  `tel` varchar(16) NOT NULL,
  PRIMARY KEY (`operatorid`) ,
   FOREIGN KEY (employeeid)
  REFERENCES employee(employeeid)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

CREATE TABLE IF NOT EXISTS `courier` ( 
  `courierid` int(11)    NOT NULL AUTO_INCREMENT,
  `empolyeeid` int(11) NOT NULL,
  `location_x` varchar(100) DEFAULT NULL,
  `location_y` varchar(100) DEFAULT NULL,
  `tel` varchar(16) NOT NULL ,
   PRIMARY KEY (`courierid`) ,
  FOREIGN KEY (empolyeeid)
  REFERENCES employee(employeeid)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE IF NOT EXISTS `shipment` (
  `shipmentid` int(11) NOT NULL AUTO_INCREMENT,
  `dataid` int(11) NOT NULL,
  `sourceid` int(11) NOT NULL,
  `returnid` int(11) NOT NULL,
  `serviceid` int(11) NOT NULL,
  `weightid` int(11) NOT NULL,
  `amount` int(11) NOT NULL,
  `paymentid` int(11) NOT NULL,
  `sdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `ddate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `statusid` int(11) NOT NULL,
  `paid` tinyint(1) NOT NULL,
  PRIMARY KEY (`shipmentid`) ,
 FOREIGN KEY (dataid)
  REFERENCES data(dataid),
  FOREIGN KEY (serviceid)
  REFERENCES service(serviceid),
    FOREIGN KEY (weightid)
  REFERENCES weight(weightid),
    FOREIGN KEY (paymentid)
  REFERENCES payment(paymentid),
    FOREIGN KEY (statusid)
  REFERENCES status(statusid)
  
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

   CREATE TABLE IF NOT EXISTS `operator_history` (
  `operatorid` int(11) NOT NULL,
  `shipmentid` int(11) NOT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,
    FOREIGN KEY (operatorid)
  REFERENCES operator(operatorid),
    FOREIGN KEY (shipmentid)
  REFERENCES shipment(shipmentid)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `base_history` (
  `baseid` int(11) NOT NULL,
  `shipmentid` int(11) NOT NULL,
  `odate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,
   FOREIGN KEY (shipmentid)
  REFERENCES shipment(shipmentid),
  FOREIGN KEY (baseid)
  REFERENCES base(baseid)
  
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `courier_history` (
  `courierid` int(11) NOT NULL,
  `shipmentid` int(11) NOT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `oncar` tinyint(1) NOT NULL,
  `delivered` tinyint(1) NOT NULL  ,
   FOREIGN KEY (shipmentid)
  REFERENCES shipment(shipmentid),
   FOREIGN KEY (courierid)
  REFERENCES courier(courierid)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
