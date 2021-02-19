import { NextComponentType, InferGetServerSidePropsType, GetServerSideProps } from 'next';
import { AppContext, AppInitialProps, AppProps } from 'next/app';
import Head from 'next/head';
import { UserContextProvider } from 'Contexts/UserContext';
import Layout from 'Components/Layout';
import GlobalStyle from 'Styles/GlobalStyle';

const App: NextComponentType<AppContext, AppInitialProps, AppProps> = ({ Component, pageProps }) => {
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

// App.getInitialProps = async ({ Component, ctx }: AppContext): Promise<AppInitialProps> => {
//     let pageProps = {};

//     if (Component.getInitialProps) {
//         pageProps = await Component.getInitialProps(ctx);
//     }

//     return { pageProps };
// }

export default App;