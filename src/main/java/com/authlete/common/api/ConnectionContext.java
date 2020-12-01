/*
 * Copyright (C) 2020 Authlete, Inc.
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
package com.authlete.common.api;


import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;


class ConnectionContext
{
    private final HttpURLConnection mCon;
    private InputStream mIn;
    private OutputStream mOut;
    private InputStream mErr;


    public ConnectionContext(HttpURLConnection connection)
    {
        mCon = connection;
    }


    public HttpURLConnection connection()
    {
        return mCon;
    }


    public int contentLength()
    {
        return mCon.getContentLength();
    }


    public void property(String key, String value)
    {
        mCon.setRequestProperty(key, value);
    }


    public void doOutput(boolean dooutput)
    {
        mCon.setDoOutput(dooutput);
    }


    public InputStream inputStream() throws IOException
    {
        if (mIn == null)
        {
            mIn = mCon.getInputStream();
        }

        return mIn;
    }


    public OutputStream outputStream() throws IOException
    {
        if (mOut == null)
        {
            mOut = mCon.getOutputStream();
        }

        return mOut;
    }


    public InputStream errorStream()
    {
        if (mErr == null)
        {
            mErr = mCon.getErrorStream();
        }

        return mErr;
    }


    public void close()
    {
        closeQuietly(mIn);
        closeQuietly(mOut);
        closeQuietly(mErr);
    }


    private static void closeQuietly(Closeable closeable)
    {
        if (closeable == null)
        {
            return;
        }

        try
        {
            closeable.close();
        }
        catch (IOException e)
        {
            // Ignored.
        }
    }
}
