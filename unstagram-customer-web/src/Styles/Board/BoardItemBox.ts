import styled from 'styled-components';

const BoardItemBox = styled.article`
    width: 100%;
    padding: 0;
    margin: 0 0 60px;

    flex-direction: column;

    border: 1px solid rgba(var(--b6a,219,219,219),1);
    border-radius: 3px;

    background: #ffffff;

    .BoardItem-Image-Wrapper{
        width: 100%;
    }
`;

export default BoardItemBox;