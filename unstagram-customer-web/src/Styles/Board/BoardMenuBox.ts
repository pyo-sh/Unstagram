import styled from 'styled-components';

const BoardMenuBox = styled.div`
    width: 100%;
    height: 40px;
    margin-top: 4px;
    padding: 0 16px;

    justify-content: flex-start;
    align-items: center;

    .BoardMenu-Button{
        padding: 8px;
        border: none;
        background: none;
        cursor: pointer;

        :focus{
            outline: none;
        }
    }

    .BoardMenu-Book{
        margin-left: auto;
    }

    .BoardMenu-Icon{
        
    }
`;

export default BoardMenuBox;