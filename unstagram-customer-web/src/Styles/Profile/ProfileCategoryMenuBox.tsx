import styled from 'styled-components';

interface IconProp {
    label: string,
    className: string,
    fill: string,
    width: number,
    height: number,
    viewBox: string
}

export const categoryIcon: Function = (props: IconProp) => {
    const { label, className, fill, width, height, viewBox } = props;

    let path: string;
    switch(label){
        case "Board":
            path = "M45 1.5H3c-.8 0-1.5.7-1.5 1.5v42c0 .8.7 1.5 1.5 1.5h42c.8 0 1.5-.7 1.5-1.5V3c0-.8-.7-1.5-1.5-1.5zm-40.5 3h11v11h-11v-11zm0 14h11v11h-11v-11zm11 25h-11v-11h11v11zm14 0h-11v-11h11v11zm0-14h-11v-11h11v11zm0-14h-11v-11h11v11zm14 28h-11v-11h11v11zm0-14h-11v-11h11v11zm0-14h-11v-11h11v11z";
            break;
        case "Saved":
            path = "M43.5 48c-.4 0-.8-.2-1.1-.4L24 29 5.6 47.6c-.4.4-1.1.6-1.6.3-.6-.2-1-.8-1-1.4v-45C3 .7 3.7 0 4.5 0h39c.8 0 1.5.7 1.5 1.5v45c0 .6-.4 1.2-.9 1.4-.2.1-.4.1-.6.1zM24 26c.8 0 1.6.3 2.2.9l15.8 16V3H6v39.9l15.8-16c.6-.6 1.4-.9 2.2-.9z";
            break;
        case "Tag":
            path = "M41.5 5.5H30.4c-.5 0-1-.2-1.4-.6l-4-4c-.6-.6-1.5-.6-2.1 0l-4 4c-.4.4-.9.6-1.4.6h-11c-3.3 0-6 2.7-6 6v30c0 3.3 2.7 6 6 6h35c3.3 0 6-2.7 6-6v-30c0-3.3-2.7-6-6-6zm-29.4 39c-.6 0-1.1-.6-1-1.2.7-3.2 3.5-5.6 6.8-5.6h12c3.4 0 6.2 2.4 6.8 5.6.1.6-.4 1.2-1 1.2H12.1zm32.4-3c0 1.7-1.3 3-3 3h-.6c-.5 0-.9-.4-1-.9-.6-5-4.8-8.9-9.9-8.9H18c-5.1 0-9.4 3.9-9.9 8.9-.1.5-.5.9-1 .9h-.6c-1.7 0-3-1.3-3-3v-30c0-1.7 1.3-3 3-3h11.1c1.3 0 2.6-.5 3.5-1.5L24 4.1 26.9 7c.9.9 2.2 1.5 3.5 1.5h11.1c1.7 0 3 1.3 3 3v30zM24 12.5c-5.3 0-9.6 4.3-9.6 9.6s4.3 9.6 9.6 9.6 9.6-4.3 9.6-9.6-4.3-9.6-9.6-9.6zm0 16.1c-3.6 0-6.6-2.9-6.6-6.6 0-3.6 2.9-6.6 6.6-6.6s6.6 2.9 6.6 6.6c0 3.6-3 6.6-6.6 6.6z";
            break;
        default:
            return null;
    }

    return (
        <svg aria-label={label} className={className} fill={fill} width={width} height={height} viewBox={viewBox}>
            <path d={path}>
            </path>
        </svg>
    );
}

const ProfileCategoryMenuBox = styled.div`
    width: 100%;

    border-top: 1px solid rgba(var(--b38,219,219,219),1);

    justify-content: center;

    .ProfileCategoryMenu-Button{
        padding: 0;

        justify-content: center;
        align-items: center;
        cursor: pointer;

        background: none;
        border: none;
        outline: none;
        color: #8e8e8e;
    }

    @media(min-width: 736px){
        .ProfileCategoryMenu-Button{
            height: 53px;
            margin: -1px 60px 0 0;

            :last-child{
                margin-right: 0;
            }
        }
        .ProfileCategoryMenu-Selected{
            color: #262626;
            font-weight: 600;

            border-top: 1px solid #262626;
        }
        .ProfileCategoryMenu-Icon{
            margin-right: 5px;
            margin-bottom: -2px;
        }
    }
    @media(max-width: 735px){
        .ProfileCategoryMenu-Button{
            height: 44px;
            margin: 0;
            flex-grow: 1;
        }
    }
`;

export default ProfileCategoryMenuBox;