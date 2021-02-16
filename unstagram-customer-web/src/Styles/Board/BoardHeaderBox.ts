import styled from 'styled-components';

const BoardHeaderBox = styled.header`
    width: 100%;
    height: 60px;
    padding: 10px 16px;

    border-bottom: 1px solid rgba(var(--ce3,239,239,239),1);

    align-items: center;

    .BoardHeader-Image-Wrapper{
        width: 32px;
        height: 32px;
        .BoardHeader-Image{
            width: 100%;
            height: 100%;

            border: 1px solid #000;
            border-radius: 50%;
        }
    }

    .BoardHeader-About{
        margin-left: 14px;

        flex-grow: 1;
        flex-direction: column;
        justify-content: center;

        .BoardHeader-Author{
            font-size: 14px;
            font-weight: 600;
        }
        .BoardHeader-Location{
            font-size: 10.5px;
            color: rgba(var(--i1d,38,38,38),1);
        }
    }
`;

export default BoardHeaderBox;