/**
 * Copyright 2022 Emmanuel Bourg
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

package net.jsign;

import java.io.IOException;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.StandardCharsets;

import org.apache.commons.compress.utils.SeekableInMemoryByteChannel;
import org.junit.Test;

import static org.junit.Assert.*;

public class ChannelUtilsTest {

    @Test
    public void testReadNullTerminatedString() throws Exception {
        String s = "ABCD\u0000";
        SeekableByteChannel channel = new SeekableInMemoryByteChannel((s).getBytes(StandardCharsets.UTF_8));

        assertEquals(s, new String(ChannelUtils.readNullTerminatedString(channel)));
    }

    @Test(expected = IOException.class)
    public void testReadNullTerminatedStringInvalid() throws Exception {
        String s = "ABCD";
        SeekableByteChannel channel = new SeekableInMemoryByteChannel((s).getBytes(StandardCharsets.UTF_8));
        ChannelUtils.readNullTerminatedString(channel);
    }
}
