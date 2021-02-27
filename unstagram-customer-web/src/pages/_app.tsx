import { GetServerSideProps, NextComponentType } from 'next';
import { AppContext, AppInitialProps, AppProps } from 'next/app';
import Head from 'next/head';
import { UserContextProvider } from 'Contexts/UserContext';
import Layout from 'Components/Layout';
import GlobalStyle from 'Styles/GlobalStyle';
import axios from 'axios';

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

export const getServerSideProps: GetServerSideProps = async (context) => {
  const cookie = context.req ? context.req.headers.cookie : '';
  axios.defaults.headers.Cookie = '';
  console.log(cookie)

  if (context.req && cookie) {
    axios.defaults.headers.Cookie = cookie;
    
  }

  return {
    props: {}
  };
}


export default App;