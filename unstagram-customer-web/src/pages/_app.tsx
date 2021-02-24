import { NextComponentType } from 'next';
import { AppContext, AppInitialProps, AppProps } from 'next/app';
import Head from 'next/head';
import { UserContextProvider } from 'Contexts/UserContext';
import Layout from 'Components/Layout';
import GlobalStyle from 'Styles/GlobalStyle';
import { AppContextType } from 'next/dist/next-server/lib/utils';
import { Router } from 'next/router';
import { useEffect } from 'react';
import createAsyncDispatcher from 'Contexts/AsyncActionUtil';

// App.getServerSideProps: GetServerSideProps = async (context) => {
//   console.dir(context.req);
//   return {
//     props: {
//       data: context.params ? context.params.user : null
//     },
//     revalidate: 1,
//   }
// }

const App: NextComponentType<AppContext, AppInitialProps, AppProps> = ({ Component, pageProps }) => {
  // const userDispatch = createAsyncDispatcher('LOGIN_USER', );
  // useEffect(() => {
  //   if(pageProps.cookie){
      
  //   }
  // }, []);
  return <>
    <Head>
      <meta name="viewport" content="width=device-width, initial-scale=1"/>
      <title>Unstagram</title>
    </Head>
    <GlobalStyle/>
    <Layout>
        <UserContextProvider>
            <Component {...pageProps}/>
        </UserContextProvider>
    </Layout>
  </>
}

App.getInitialProps = async (context: AppContextType<Router>): Promise<AppInitialProps> => {
    const { ctx } = context;
    const pageProps = {
      cookie: ctx.req?.headers.cookie
    };
    console.log(ctx.req?.headers.cookie);

    return { pageProps };
}

export default App;