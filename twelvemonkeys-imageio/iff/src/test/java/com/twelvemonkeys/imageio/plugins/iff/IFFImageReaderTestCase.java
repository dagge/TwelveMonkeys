/*
 * Copyright (c) 2008, Harald Kuhr
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name "TwelveMonkeys" nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.twelvemonkeys.imageio.plugins.iff;

import com.twelvemonkeys.imageio.util.ImageReaderAbstractTestCase;

import javax.imageio.spi.ImageReaderSpi;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

/**
 * IFFImageReaderTestCase
 *
 * @author <a href="mailto:harald.kuhr@gmail.com">Harald Kuhr</a>
 * @author last modified by $Author: haraldk$
 * @version $Id: IFFImageReaderTestCase.java,v 1.0 Apr 1, 2008 10:39:17 PM haraldk Exp$
 */
public class IFFImageReaderTestCase extends ImageReaderAbstractTestCase<IFFImageReader> {
    // TODO: Need test for IFF PBM
    protected List<TestData> getTestData() {
        return Arrays.asList(
                // 32 bit - Ok
                new TestData(getClassLoaderResource("/iff/test.iff"), new Dimension(300, 200)), // 32 bit
                // 24 bit - Ok
                new TestData(getClassLoaderResource("/iff/survivor.iff"), new Dimension(800, 600)), // 24 bit
                // HAM6 - Ok (a lot of visual "fringe", would be interesting to see on a real HAM display)
                new TestData(getClassLoaderResource("/iff/A4000T_HAM6.IFF"), new Dimension(320, 512)), // ham6
                // HAM8 - Ok (PackBits decoder chokes on padding byte)
                new TestData(getClassLoaderResource("/iff/A4000T_HAM8.IFF"), new Dimension(628, 512)), // ham8
                // 8 color indexed - Ok
                new TestData(getClassLoaderResource("/iff/AmigaBig.iff"), new Dimension(300, 200)), // 8 color
                // 8 color indexed - Ok
                new TestData(getClassLoaderResource("/iff/AmigaAmiga.iff"), new Dimension(200, 150)), // 8 color
                // Ok (PackBits decoder chokes on padding byte)
                new TestData(getClassLoaderResource("/iff/Abyss.iff"), new Dimension(320, 400))
        );
    }

    protected ImageReaderSpi createProvider() {
        return new IFFImageReaderSpi();
    }

    protected Class<IFFImageReader> getReaderClass() {
        return IFFImageReader.class;
    }

    protected List<String> getFormatNames() {
        return Arrays.asList("iff");
    }

    protected List<String> getSuffixes() {
        return Arrays.asList("iff", "ilbm", "ham", "ham8", "lbm");
    }

    protected List<String> getMIMETypes() {
        return Arrays.asList("image/iff", "image/x-iff");
    }
}
