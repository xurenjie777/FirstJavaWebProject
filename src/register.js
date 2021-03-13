import React, { Component } from 'react';
import { message,Layout,Breadcrumb,Input,Avatar,Button,Radio } from 'antd';
import './register.css';
import axios from "axios";



export default class Register extends Component{
    constructor (props) {
        super(props);
        this.state = {
            username:'',
            password:'',
            sex:'',
            level:1,
            department:''
        }
    }

    render() {
        return (
            <div id="login-box">
                <h1>Sign up</h1>
                <div className="form">
                    <div className="item">
                        <i className="fa fa-user-circle-o" aria-hidden="true"></i>
                        <input allowClear={true} type="text" placeholder="Username" value={this.state.username}
                               onChange={(e) => {
                                   this.setState({username: e.target.value})
                               }}/>
                    </div>
                    <div className="item">
                    <i className="fa fa-key" aria-hidden="true"></i>
                    <input allowClear={true} type="password" placeholder="Password" value={this.state.password}
                           onChange={(e) => {
                               this.setState({password: e.target.value})
                           }}/>
                    </div>
                    <div className="item">
                        <i className="fa fa-envelope" aria-hidden="true"></i>
                        <input allowClear={true} type="text" placeholder="Sex" value={this.state.sex}
                               onChange={(e) => {
                                   this.setState({sex: e.target.value})
                               }}/>
                    </div>
                    <div className="item">
                        <i className="fa fa-key" aria-hidden="true"></i>
                        <input allowClear={true} type="Department" placeholder="Department" value={this.state.department}
                               onChange={(e) => {
                                   this.setState({department: e.target.value})
                               }}/>
                    </div>
                    <div>
                    <select  value={this.state.level} onChange={(e)=>{this.setState({level:e.target.value})}}>
                        <option value="1">员工</option>
                        <option value="2">部门管理员</option>
                        <option value="3">总管理员</option>
                    </select>
                </div>
                    <div className="button" onClick={this.register}>
                        <button>Register</button>
                    </div>
                </div>
            </div>
        );
    }

    register = (e) => {
        e.preventDefault();
        if(this.state.username != '' && this.state.password != '' && this.state.sex != '' && this.state.department != ''){
            let data = new FormData();
            data.append('username',this.state.username);
            data.append('password',this.state.password);
            data.append('sex',this.state.sex);
            data.append('department',this.state.department);
            axios.post('http://localhost:8080/user/signup',data).then(res=>{
                if(res.data.code == 100){
                    message.info('注册成功');
                    this.props.history.push({ pathname: '/'});
                }
                else {
                    message.info(res.data.msg);
                }
            })
        }
        else {
            message.info('不能有空');
        }
    }

}

