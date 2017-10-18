/*
 * Copyright 2017, OpenSkywalking Organization All rights reserved.
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
 *
 * Project repository: https://github.com/OpenSkywalking/skywalking
 */

package org.skywalking.apm.plugin.xmemcached.v2;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.skywalking.apm.agent.core.plugin.interceptor.enhance.EnhancedInstance;

@RunWith(MockitoJUnitRunner.class)
public class XMemcachedConstructorWithInetSocketAddressListArgInterceptorTest {

    private XMemcachedConstructorWithInetSocketAddressListArgInterceptor interceptor;

    @Mock
    private EnhancedInstance enhancedInstance;

    @Before
    public void setUp() throws Exception {
        interceptor = new XMemcachedConstructorWithInetSocketAddressListArgInterceptor();
    }

    @Test
    public void onConstructWithInetSocketAddressList() {
        List<InetSocketAddress> inetSocketAddressList = new ArrayList<InetSocketAddress>();
        inetSocketAddressList.add(new InetSocketAddress("127.0.0.1", 11211));
        inetSocketAddressList.add(new InetSocketAddress("127.0.0.2", 11211));
        interceptor.onConstruct(enhancedInstance, new Object[]{inetSocketAddressList});

        verify(enhancedInstance, times(1)).setSkyWalkingDynamicField("127.0.0.1:11211;127.0.0.2:11211");
    }
}