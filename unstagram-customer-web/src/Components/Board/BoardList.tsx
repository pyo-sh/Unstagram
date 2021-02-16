import React from 'react';
import BoardListBox from 'Styles/Board/BoardListBox';
import BoardItem from './BoardItem';

const BoardList: React.FC = () => {
  return (
    <BoardListBox>
      <BoardItem></BoardItem>
    </BoardListBox>
  );
}

export default BoardList;
