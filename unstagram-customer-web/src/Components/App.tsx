import React from 'react';
import SignIn from 'Components/Sign/SignIn';
import StoryList from 'Components/Story/StoryList';
import BoardList from 'Components/Board/BoardList';
import Header from 'Components/Main/Header';
import SideProfile from 'Components/Main/SideProfile';

const App: React.FC = () => {
  return (
    <div className="App">
      <Header/>
      <SideProfile/>
      <StoryList></StoryList>
      <BoardList></BoardList>
      <SignIn/>
    </div>
  );
}

export default App;
