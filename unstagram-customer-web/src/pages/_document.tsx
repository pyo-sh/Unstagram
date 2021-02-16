import Document, { Html, Head, Main, NextScript } from 'next/document';
import React from 'react';

class UnstagramDocument extends Document{
    render(){
        return(
            <Html>
                <Head>
                    <link rel="icon" href="/instagram.ico" />
                    <link rel="manifest" href="/manifest.json"/>
                    <link href="https://fonts.googleapis.com/css?family=Dancing+Script" rel="stylesheet"></link>
                </Head>
                <body>
                    <div id="root">
                        <Main />
                        <NextScript />
                    </div>
                </body>
            </Html>
        );
    }
}

export default UnstagramDocument;