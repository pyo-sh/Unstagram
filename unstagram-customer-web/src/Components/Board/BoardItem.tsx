import React from 'react';
import BoardItemBox from 'Styles/Board/BoardItemBox';
import BoardHeader from './BoardHeader';
import BoardMenu from './BoardMenu';
import BoardComment from './BoardComment';
import BoardContent from './BoardContent';

const BoardItem: React.FC = () => {
  return (
    <BoardItemBox>
      <BoardHeader/>
      <div className="BoardItem-Image-Wrapper">
        <img></img>
      </div>
      <BoardMenu/>
      <BoardContent/>
      <BoardComment/>
    </BoardItemBox>
  );
}

export default BoardItem;
