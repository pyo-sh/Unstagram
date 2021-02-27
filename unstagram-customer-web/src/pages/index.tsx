import IndexWrapper from 'Styles/Pages/IndexWrapper';
import Main from 'Components/Main/Main'
import SignIn from 'Components/Sign/SignIn';
import {useUserState} from 'Contexts/UserContext';

const Home = () => {
  const { isLoggedIn } = useUserState();

  return (isLoggedIn
      ? <Main/>
      : <IndexWrapper>
        <SignIn/>
      </IndexWrapper>
  );
};

export default Home;