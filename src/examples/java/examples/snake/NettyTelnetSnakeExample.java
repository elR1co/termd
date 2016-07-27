/*
 * Copyright 2015 Julien Viet
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package examples.snake;

import io.termd.core.telnet.TelnetTtyBootstrap;
import io.termd.core.telnet.netty.NettyTelnetBootstrap;

import java.util.concurrent.TimeUnit;

public class NettyTelnetSnakeExample {

  public synchronized static void main(String[] args) throws Exception {
    TelnetTtyBootstrap bootstrap = new TelnetTtyBootstrap(new NettyTelnetBootstrap()).setHost("localhost").setPort(4000);
    bootstrap.start(new SnakeGame()).get(10, TimeUnit.SECONDS);
    System.out.println("Telnet server started on localhost:4000");
    NettyTelnetSnakeExample.class.wait();
  }
}
