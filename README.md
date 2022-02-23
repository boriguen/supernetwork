# _SuperNetwork_

##### _This project mainly focuses on providing an HttpURLConnection wrapper as library to help developers do GET and POST requests seamlessly - February 2022_

#### By _**Boris Guenebaut**_

## Description

The **sdk** module includes the logic to wrap _HttpURLConnection_ and provide easy public methods to make GET and POST requests.
On top of that, the **sdk** offers the option to log the requests and their associated response code.

## Setup

1. Use Android Studio or Gradle script to build the app
2. Run the tests in module **sdk** if needed

## Engineering choices

### Network specifics

**HttpURLConnection** is used as the core to execute the requests. Coroutines are used to simplify the execution of network requests in a background thread.


### Library packaging

A Gradle task could be created to pack the **sdk** module into a .aar. Another task could publish this archive onto Maven or Artifactory.

### Local persistence

**Room** is used to persist the network logs on the device.

## Legal

Copyright (c) 2022 **_Boris Guenebaut_**

This software is licensed under the MIT license.

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.