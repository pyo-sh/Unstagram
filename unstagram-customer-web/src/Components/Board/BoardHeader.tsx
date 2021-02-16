import React from 'react';
import BoardHeaderBox from 'Styles/Board/BoardHeaderBox';
import BoardHeaderMenu from './BoardHeaderMenu';

const BoardHeader: React.FC = () => {
  return (
    <BoardHeaderBox>
      <div className="BoardHeader-Image-Wrapper">
        <img className="BoardHeader-Image"/>
      </div>
      <div className="BoardHeader-About">
        <div className="BoardHeader-Author">identification</div>
        <div className="BoardHeader-Location">where</div>
      </div>
      <BoardHeaderMenu/>
    </BoardHeaderBox>
  );
}

export default BoardHeader;
