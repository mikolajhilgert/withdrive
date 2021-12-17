import React from 'react';
import { components } from 'react-select';

const CustomOption = ({ children, ...props }) => {
  const { onMouseMove, onMouseOver, ...rest } = props.innerProps;
  const newProps = { ...props, innerProps: rest };
  return (
    <components.Option {...newProps}>
      {children}
    </components.Option>
  );
};
export default CustomOption;