import React from 'react';
import MainBox from 'Styles/Main/MainBox';
import SideProfile from 'Components/Main/SideProfile';
import StoryList from 'Components/Story/StoryList';
import BoardList from 'Components/Board/BoardList';

const Main: React.FC = () => {
  return (
    <MainBox>
      <div className="Main-Wrapper">
        <div className="Main-Scroll">
            <StoryList/>
            <BoardList/>
        </div>
        <SideProfile/>
      </div>
    </MainBox>
  );
}

export default Main;
