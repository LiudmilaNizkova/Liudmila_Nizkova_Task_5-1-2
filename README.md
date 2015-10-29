create a testng.xml file  int the root of directory


<!DOCTYPE suite SYSTEM "http://testng.org/testng-1.0.dtd">
<suite name="Module_5_1_2 suite" parallel="methods" thread-count="1">
    <test name="Test1" annotations="JDK">
        <classes>
            <class name="by.epam.tests.YandexMailTest" />
        </classes>
    </test>
</suite>
