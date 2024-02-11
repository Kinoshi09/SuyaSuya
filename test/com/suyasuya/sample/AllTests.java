package com.suyasuya.sample;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({ countATest.class, squareTest.class })
public class AllTests {

}
